package cn.pw.studyJavaDemo.GC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模拟风控系统定时去检测某人的信用记录。
 */

public class FullGC_Problem {
    //设定一个单例的定时调度的线程池，核心线程数50个，拒绝策略用的是最旧任务抛弃策略。
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    //主线程
    public static void main(String[] args) throws Exception {
        //线程池设定最大线程数50，前面设置了核心线程数50，因此一启动就有50个线程在线程池里。
        executor.setMaximumPoolSize(50);
        //每100ms执行modelFit()方法
        for (;;){
            modelFit();
            Thread.sleep(100);
        }
    }


    private static void modelFit(){
        //每次进来先获取装有100个cardInfo的List
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            //每个cardInfo启动一个线程，调用他的m()方法，延时2S启动第一个，以后每3S启动一个
            executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();

            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();
        public void m() {

        }
    }

    //丢100个CardInfo到List里面返回。
    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }
        return taskList;
    }
}
