package top.xeonwang.project01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * 中心移动平均的输入数据
 *
 * @author Chen Q.
 */
@Data
@AllArgsConstructor
public class DNode {
    private LocalDate date;
    private Double data;
}
