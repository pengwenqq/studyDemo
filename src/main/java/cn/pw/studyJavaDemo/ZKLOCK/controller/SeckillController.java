package cn.pw.studyJavaDemo.ZKLOCK.controller;

import cn.pw.studyJavaDemo.ZKLOCK.ZKLock;
import cn.pw.studyJavaDemo.ZKLOCK.dao.RecordDao;
import cn.pw.studyJavaDemo.ZKLOCK.dao.StockDao;
import cn.pw.studyJavaDemo.ZKLOCK.entity.Record;
import cn.pw.studyJavaDemo.ZKLOCK.entity.Stock;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pwZkLock")
@Log4j
public class SeckillController {
    @Autowired
    RecordDao recordDao;
    @Autowired
    StockDao stockDao;

    private static final Integer STOCK_ID = 1;
    private static ZKLock zkLock =  new ZKLock("139.224.119.73:2181,139.224.119.73:2182,139.224.119.73:2183,139.224.119.73:2184","/lockPath");

    /**
     * 模拟秒杀的并发场景
     * @return true/false
     * @desc
     */
    @RequestMapping(value = "seckKill",method = RequestMethod.GET)
    public String seckKill(){
        try {
            //1.加锁
            zkLock.lock();
            //debug锁重入
            /*zkLock.lock();*/
//            zkLock.unlock();
//            zkLock.unlock();
            //2.查询库存
            Stock stock = stockDao.getOne(STOCK_ID);
            //3.判断库存
            if(stock.getStockNum() > 0){
                Record record = new Record();
                record.setStockRecord(stock.getStockNum());
                //保存记录
                recordDao.save(record);
                stock.setStockNum(stock.getStockNum()-1);
                //库存减1
                stockDao.save(stock);
                return "获取库存成功";
            }else {
                return "商品卖完了";
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            //6.解锁
            zkLock.unlock();
        }
    }
}
