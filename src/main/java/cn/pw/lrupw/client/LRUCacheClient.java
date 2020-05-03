package cn.pw.lrupw.client;

import cn.pw.lrupw.entity.LRUCacheNode;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auth:pengwen
 * @desc:
 * 运用你所掌握的数据结构，设计和实现一个LRU (最近最少使用) 缓存机制。它应该支持以下操作
 * 1:获取数据 get 和 写入数据 put 。
 * 2:获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 3:写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 4:在 O(1) 时间复杂度内完成这两种操作。
 * @date:2020/03/29
 */
public class LRUCacheClient {
    private Map<String,LRUCacheNode> lruCacheMap;
    private int capacity;
    private int size;
    private LRUCacheNode headNode;
    private LRUCacheNode tailNode;
    public final static String NONE_VALUE_RETURN = "-1";

    //初始化客户端
    public LRUCacheClient(int capacity) {
        //1.创建链表头和尾节点,头尾护指
        headNode = new LRUCacheNode();
        tailNode = new LRUCacheNode();
        headNode.setNextNode(tailNode);
        tailNode.setPreNode(headNode);
        //2.初始化内存大小,内存计数
        this.capacity = capacity;
        //当前存储个数
        this.size = 0;
        //3.初始化内存map
        lruCacheMap = new ConcurrentHashMap();
    }

    public String get(String key) {
        //1.将获取到的节点放到链表头部后标记为最近使用过的数据,返回获取到返回节点的值
        if(null != lruCacheMap.get(key)){
            LRUCacheNode getlruCacheNode= lruCacheMap.get(key);
            getlruCacheNode.moveTo(getlruCacheNode,headNode);
            return getlruCacheNode.getValue();
        }
        //2.获取不到值，返回-1
        return NONE_VALUE_RETURN;
    }

    public void put(String key, String value) {
        //1.先去查map中此key是否存在，如果存在则更新，如果不存在则放入
        LRUCacheNode searchNode = lruCacheMap.get(key);
        if(null != searchNode){
            //存在则更新，把当前节点移动到头节点后
            searchNode.setValue(value);
            searchNode.moveTo(searchNode,headNode);
        }else {
            //如果不存在，需要新增一个节点至头部,map中加入值,缓存中个数加1
            LRUCacheNode addCacheNode = new LRUCacheNode();
            addCacheNode.setKey(key);
            addCacheNode.setValue(value);
            addCacheNode.addNode(addCacheNode,headNode);
            lruCacheMap.put(key,addCacheNode);
            size++;
            // 此时需要判断新增后内存大小是否超过设置大小,不超过不用特别处理，超过需要特别处理
            if(size > capacity){
                //超过，需要弹出尾部节点
                tailNode.popTailNode(tailNode);
                size--;
            }
        }
    }

    public String showLikedList() {
        LinkedList<LRUCacheNode> nodeLinkedList = new LinkedList<>();
        //递归构建list
        buildLikedList(nodeLinkedList,headNode.getNextNode());
        return nodeLinkedList.toString();
    }

    private void buildLikedList(LinkedList<LRUCacheNode> nodeLinkedList,LRUCacheNode currentNode) {
        if(null != currentNode.getNextNode()){
            nodeLinkedList.add(currentNode);
            buildLikedList(nodeLinkedList,currentNode.getNextNode());
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */