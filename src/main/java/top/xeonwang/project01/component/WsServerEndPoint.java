package top.xeonwang.project01.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chen Q.
 */
@ServerEndpoint("/weshr")
@Component
@Slf4j
public class WsServerEndPoint {

    public static final Map sessionMap = new ConcurrentHashMap();

    @OnOpen
    public void onOpen(Session session) {
        URI requestURI = session.getRequestURI();
        log.info("connect success: " + requestURI.toString());
    }

    @OnClose
    public void onClose(Session session) {
        URI requestURI = session.getRequestURI();
        System.out.println("connection close: " + requestURI.toString());
    }

    @OnMessage
    public String onMsg(String text) {
        return "server 发送：" + text;
    }

}
