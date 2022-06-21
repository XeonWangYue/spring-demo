package top.xeonwang.project01.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.xeonwang.project01.mapper.PrivMapper;
import top.xeonwang.project01.model.Privs;

/**
 * @author Chen Q.
 */
@RestController
public class InsertPrivilege {

    @Autowired
    PrivMapper mapper;

    @PostMapping("/add")
    public Object add(@RequestBody Privs privs) {
        mapper.insert(privs);
        return privs.getId();
    }

    @GetMapping("/find")
    public Object find() {
        return mapper.selectList(null);
    }

    @PutMapping("/modify")
    public Object modify(@RequestBody Privs privs) {
        return mapper.updateById(privs);
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestBody Privs privs) {
        return mapper.deleteById(privs);
    }
}
