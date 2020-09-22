package studyJavaDemo.ZKLOCK;

import cn.pw.studyJavaDemo.ZKLOCK.ZKLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZKLockTest {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void testLock() throws Exception{
        ZKLock zkLock =  new ZKLock("139.224.119.73:2181","/lockPath");
        int[] num = {0};
        long start = System.currentTimeMillis();
        for (int i=0;i<100;i++) {
            executorService.submit(()->{
                try {
                    zkLock.lock();
                    num[0]++;
                }catch (Exception e){
                    throw new RuntimeException(e);
                }finally {
                    zkLock.unlock();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        log.info("耗时:{}",System.currentTimeMillis());
        System.out.println(num[0]);

    }
}
