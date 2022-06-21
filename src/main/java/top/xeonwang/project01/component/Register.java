package top.xeonwang.project01.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.xeonwang.project01.model.AlgRunable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Q.
 */
@Component
public class Register {
    Map map = new HashMap();

    @Bean(name = "Register")
    public Map getAlgs() {
        return this.map;
    }

    public String doCommand(int commandId, Map<String, Object> param) {
        AlgRunable alg = (AlgRunable) map.get(commandId);

        return alg.run((String) param.get("type"), (String) param.get("data"));
    }
}
