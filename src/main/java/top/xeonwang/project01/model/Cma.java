package top.xeonwang.project01.model;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author Chen Q.
 */
@Data
public class Cma {
    LinkedList<DNode> list;
    Integer time;
}
