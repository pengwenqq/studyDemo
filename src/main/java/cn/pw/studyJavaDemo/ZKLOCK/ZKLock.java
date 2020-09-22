package cn.pw.studyJavaDemo.ZKLOCK;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ZKLock {
    private CuratorFramework client;
    private InterProcessLock lock;


    public ZKLock(String zkAddress,String lockPath){
        //1.Connect to zk
        client = CuratorFrameworkFactory.newClient(zkAddress,
                new RetryNTimes(5,5000)
        );
        client.start();
        if(client.getState() == CuratorFrameworkState.STARTED){
            log.info("zk client start successfully! zkAddress={},lockPath={}",zkAddress,lockPath);
        }else {
            throw new RuntimeException("zk client start failed!");
        }
        this.lock = defaultLock(lockPath);
    }

    private InterProcessLock defaultLock(String lockPath) {
        return new InterProcessMutex(client,lockPath);
    }

    public void lock(){
        try {
            this.lock.acquire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tryLock(){
        boolean flag;
        try {
            flag = this.lock.acquire(0, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean tryLock(long time,TimeUnit timeUnit){
        boolean flag;
        try {
            flag = this.lock.acquire(time, timeUnit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }


    public void unlock() {
        try {
            this.lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
