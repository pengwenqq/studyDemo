package cn.pw.studyJavaDemo.LRU.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @auth:pengwen
 * @desc:构成双向链表的节点类定义
 * @date:2020/03/29
 */
@Data
@ToString
public class LRUCacheNode {
    //当前节点的key
    private String key;
    //当前节点的value
    private String value;
    //前一个节点
    @ToString.Exclude
    private LRUCacheNode preNode;
    //后一个节点
    @ToString.Exclude
    private LRUCacheNode nextNode;

    //在链表某处添加节点
    public void addNode(LRUCacheNode addNode, LRUCacheNode preNode) {
        //1.当前节点设置（自己的前面是preNode，自己的后面是preNode的nextNode）
        addNode.preNode = preNode;
        addNode.nextNode = preNode.nextNode;
        //2.preNode.nextNode节点设置(preNode.nextNode的preNode改为addNode)
        preNode.nextNode.preNode = addNode;
        //3.preNode节点设置（preNode节点后面的节点改为addNode）
        preNode.nextNode = addNode;
    }

    //在链表某处删除节点
    public void removeNode(LRUCacheNode removeNode) {
        //1.将removeNode.preNode.nextNode设置为removeNode.nextNode
        removeNode.preNode.nextNode = removeNode.nextNode;
        //2.将removeNode.nextNode.preNode设置为removeNode.preNode
        removeNode.nextNode.preNode = removeNode.preNode;
    }

    //将其中一个节点移动至某处
    public void moveTo(LRUCacheNode moveNode, LRUCacheNode preNode) {
        removeNode(moveNode);
        addNode(moveNode, preNode);
    }

    //弹出链表尾部节点
    public LRUCacheNode popTailNode(LRUCacheNode tailNode) {
        LRUCacheNode popNode = tailNode.preNode;
        removeNode(tailNode.preNode);
        return popNode;
    }

}