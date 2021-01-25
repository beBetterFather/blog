package com.jsw.shiro.session;

import com.jsw.entity.Tmenu;
import com.jsw.entity.Tuser;
import com.jsw.service.TuserService;
import com.jsw.util.enums.UserOnlineStatusEnum;
import com.jsw.util.mapper.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * session会话监听
 * @author jsw
 *
 */
public class MySessionListener implements SessionListener {

    private static final Logger log = LoggerFactory.getLogger(MySessionListener.class);

    @Resource
    private TuserService tuserService;

    /**
     * 会话创建时触发
     * @param session
     */
    @Override
    public void onStart(Session session) {

    }

    /**
     * 会话退出时触发
     * @param session
     */
    @Override
    public void onStop(Session session) {
    }

    /**
     * 会话过期时触发
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
    }
}
