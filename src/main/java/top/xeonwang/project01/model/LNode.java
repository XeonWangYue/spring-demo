package top.xeonwang.project01.model;

import lombok.Data;

/**
 * @author Chen Q.
 */
@Data
public class LNode {
    private LNode next, pre;
    private Integer key;
    private Object value;
}
