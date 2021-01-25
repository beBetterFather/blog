package com.jsw.shiro.session;

import com.jsw.entity.Tmenu;
import com.jsw.entity.Tuser;
import com.jsw.service.TuserService;
import com.jsw.util.enums.UserOnlineStatusEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

public class MyNewSessionListener implements AuthenticationListener {

    @Resource
    private TuserService tuserService;

    @Override
    public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) token.getPrincipal();
        Example example = new Example(Tuser.class);
        example.or().andEqualTo("userName", userName);
        Tuser user = (Tuser)tuserService.selectByExample(example).get(0);
        user.setOnline(UserOnlineStatusEnum.ONLINE.getCode());
        tuserService.updateNotNull(user);
    }

    @Override
    public void onFailure(AuthenticationToken token, AuthenticationException ae) {
        String userName = (String) token.getPrincipal();
        Example example = new Example(Tuser.class);
        example.or().andEqualTo("userName", userName);
        Tuser user = (Tuser)tuserService.selectByExample(example).get(0);
        user.setOnline(UserOnlineStatusEnum.FAILURE.getCode());
        tuserService.updateNotNull(user);
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        Example example = new Example(Tuser.class);
        example.or().andEqualTo("userName", userName);
        Tuser user = (Tuser)tuserService.selectByExample(example).get(0);
        user.setOnline(UserOnlineStatusEnum.OFFLINE.getCode());
        tuserService.updateNotNull(user);
    }
}
