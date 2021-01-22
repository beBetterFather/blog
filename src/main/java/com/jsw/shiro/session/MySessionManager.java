package com.jsw.shiro.session;

import com.jsw.util.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

import static org.apache.shiro.web.servlet.ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE;

/**
 * @Author: jsw
 * @Discription 自定义SessionMananger 为什么要自定义自定义SessionMananger?
 * 原因:
 * 因为前后端分离的情况下 不是靠session,而是靠token去交互,因此需要自定义这个sessionId的获取
 * 即重写父类的方法(父类是从头中拿sessionId)
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final Logger log = LoggerFactory.getLogger(MySessionManager.class);

    //这个key放在请求头中,可以自己定义 ,通常是设置为token或者authorization
    private static final String AUTHORIZATION = "token";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (StringUtils.isNotEmpty(token)) {
            request.setAttribute(REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            log.info("调用MySessionManager获取sessionId=" + token);
            return token;
        } else {
            return super.getSessionId(request, response);
        }
    }

}
