package cn.pw.studyJavaDemo.JUC1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程与高并发练习题
 * 练习题1：
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够给支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class BlockContainer {

    Map map = new HashMap();
    Lock lock = new ReentrantLock();

    public BlockContainer(int size) {
        map =  new ConcurrentHashMap(size);
        lock.lock();
    }

    public void put(String key,Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public int getCount(){
        return map.size();
    }

}
