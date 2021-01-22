package com.jsw.shiro.session;

import com.jsw.util.mapper.JsonMapper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * session会话监听
 * @author jsw
 *
 */
public class MySessionListener implements SessionListener {

    private static final Logger log = LoggerFactory.getLogger(MySessionListener.class);

    /**
     * 会话创建时触发
     * @param session
     */
    @Override
    public void onStart(Session session) {
        log.info("创建会话 {}", JsonMapper.toJsonString(session));
    }

    /**
     * 会话退出时触发
     * @param session
     */
    @Override
    public void onStop(Session session) {
        JsonMapper.toJsonString(session);
    }

    /**
     * 会话过期时触发
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        JsonMapper.toJsonString(session);
    }
}
