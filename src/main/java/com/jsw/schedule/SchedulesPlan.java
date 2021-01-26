package com.jsw.schedule;

import com.jsw.entity.Tuser;
import com.jsw.service.TuserService;
import com.jsw.service.TworkService;
import com.jsw.util.Collections3;
import com.jsw.util.enums.UserOnlineStatusEnum;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 *
 */
@Component                //实例化
@Configurable             //注入bean
@EnableScheduling         //开启计划任务
public class SchedulesPlan {

    @Resource
    @Qualifier("mySessionDao")
    private SessionDAO sessionDAO;

    @Resource
    private TuserService tuserService;

    @Resource
    private TworkService workService;

    /**
     * 用于会话管理的定时任务
     * 每10秒执行一次
     */
    @Scheduled(cron = " 0/10 * * * * ? ")
    public void updateOnlineFlagBySessionInfo(){

        //1、通过shiro的sessionDao获取所有在线的用户principle
        Collection<Session> sessions = sessionDAO.getActiveSessions();

        //2、将用户名统计在一个LIST中
        List<String> onlineUsers = Lists.newArrayList();
        if(!Collections3.isEmpty(sessions)){
            sessions.stream().forEach(t->{
                SimplePrincipalCollection collection = (SimplePrincipalCollection)t.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if(collection != null){
                    onlineUsers.add((String)collection.getPrimaryPrincipal());
                }
            });
        }

        //3、更新人员表的online状态标志位
        Example example = new Example(Tuser.class);
        List<Tuser> list = tuserService.selectByExample(example);
        list.stream().forEach(t->{
            if(onlineUsers.contains(t.getUserName())){
                t.setOnline(UserOnlineStatusEnum.ONLINE.getCode());
            }else{
                t.setOnline(UserOnlineStatusEnum.OFFLINE.getCode());
            }
            tuserService.updateNotNull(t);
        });
    }

    /**
     * 更新
     */
    @Scheduled(cron = " 0/10 * * * * ? ")
    public void updateOverTime(){
        workService.updateOverTime();
    }
}
