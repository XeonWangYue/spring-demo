package top.xeonwang.project01.model;

import lombok.Data;

/**
 * @author Chen Q.
 */
@Data
public class DualLinkList {
    private LNode head, rear;
    private int size;

    public DualLinkList() {
        head = new LNode();
        rear = new LNode();
        head.setNext(rear);
        rear.setPre(head);
        size = 0;
    }

    public void pushHead(LNode node) {
        size++;
    }

    public void moveToHead(Integer index) {

    }

    public void deleteRear(LNode node) {
        size--;
    }
}
