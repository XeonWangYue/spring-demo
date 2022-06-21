package top.xeonwang.project01.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Chen Q.
 */
@TableName("auth_privilege")
@Data
public class Privs {
    Long id;
    String action;
    String root;
    String type;
    String parent;
    String description;
    String label;
    String url;
}
