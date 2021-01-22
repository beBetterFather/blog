package com.jsw.shiro.session;

import com.jsw.util.lang.DateUtils;
import com.jsw.util.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 自定义SessionIdGenerator
 * @author jsw
 *
 */
public class MySessionIdGenerator implements SessionIdGenerator {

    @Override
    public Serializable generateId(Session session) {
        //可以使用更加复杂的,例如加解密算法等等算法
        return StringUtils.join("xdclass",
                DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"),
                UUID.randomUUID().toString().replace("-", ""));
    }

}
