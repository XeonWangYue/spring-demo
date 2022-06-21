package top.xeonwang.project01.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xeonwang.project01.model.DNode;
import top.xeonwang.project01.component.Register;
import top.xeonwang.project01.model.Cma;
import top.xeonwang.project01.annotation.AlgMethod;
import top.xeonwang.project01.model.CommandID;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * @author Chen Q.
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    Register register;


    @GetMapping("/mysql")
    public String getMySQL() {
        return "";
    }

    @GetMapping("/middle")
    public String middle() {

        LinkedList<DNode> testData = new LinkedList<>();
        Random random = new Random();
        LocalDate of = LocalDate.of(1900, 1, 1);
        for (int i = 0; i < 10000; i++) {
            testData.add(new DNode(of, random.nextDouble()));
            of = of.plusDays(1);
        }

        Cma cma = new Cma();
        cma.setList(testData);
        cma.setTime(5);
        String s = JSON.toJSONString(cma);
        Map map = new HashMap<String, Object>();

        map.put("type", "CenteredMoveAverage");
        map.put("data", s);

        return register.doCommand(CommandID.CENTERED_MOVE_AVERAGE.ordinal(), map);

    }


    @GetMapping("/aspect")
    public Object testAspect() {
        Object test = test();
        return "";
    }


    public Object test() {
        log.info("test");
        return new Object();
    }
}
