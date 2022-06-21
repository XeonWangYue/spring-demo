package top.xeonwang.project01.util;

import top.xeonwang.project01.model.DualLinkList;
import top.xeonwang.project01.model.LNode;

import java.util.HashMap;

/**
 * @author Chen Q.
 */
public class LRUCache {
    private HashMap<Integer, Object> map;
    private DualLinkList list;

    int capacity;

    public LRUCache() {
        map = new HashMap<>();
        list = new DualLinkList();
        capacity = 5;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        list = new DualLinkList();
        this.capacity = capacity;
    }

    public void use(Integer key, Object value) {
        //先搜索hashmap是否存在
        Object o = map.get(key);

        if (null == o) { //不存在
            if (map.size() == this.capacity) { //超过容量

            } else { //未超过
                map.put(key, value);
                LNode lNode = new LNode();
                lNode.setKey(key);
                lNode.setValue(value);
                list.pushHead(lNode);
            }

        } else { // 存在
            list.moveToHead(key);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache();

    }
}
