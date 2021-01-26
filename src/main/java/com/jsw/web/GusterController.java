package com.jsw.web;

import com.github.pagehelper.PageHelper;
import com.jsw.entity.Tuser;
import com.jsw.entity.Twork;
import com.jsw.model.JqgridBean;
import com.jsw.model.PageRusult;
import com.jsw.service.TuserService;
import com.jsw.util.Collections3;
import com.jsw.util.enums.UserOnlineStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/guster")
public class GusterController {

    @Resource
    private TuserService tuserService;

    @Resource
    @Qualifier("mySessionDao")
    private SessionDAO sessionDao;

    /**
     * 获取所有用户的登录状态，登录状态维护在数据库中，t_User表中有一个字段online
     * @param httpSession
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpSession httpSession){
        Example example = new Example(Tuser.class);
        example.or().andNotEqualTo("userName", "admin");
        List<Tuser> users = tuserService.selectByExample(example);
        httpSession.setAttribute("users", users);
        return "/guster/contacts";
    }

    /**
     * 获取所有用户的登录状态，登录状态维护在数据库中，t_User表中有一个字段online
     * @param userName
     * @return
     */
    @RequestMapping("/forceLogout")
    public String forceLogout(@PathParam("userName") String userName){
        //总体思路，主要是删除掉对应用户的session
        Collection<Session> sessions = sessionDao.getActiveSessions();
        sessions.stream()
                .filter(t-> t.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null)
                .filter(t->
                ((SimplePrincipalCollection)t.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)).getPrimaryPrincipal()
                        .equals(userName)).forEach(t->{
                        //1、将被管理员手动退出的状态记录到数据库中
                        Example example = new Example(Tuser.class);
                        List<Tuser> list = tuserService.selectByExample(example);
                        Tuser user = list.get(0);
                        user.setOnline(UserOnlineStatusEnum.FORCELOGOUT.getCode());
                        tuserService.updateNotNull(user);
                        //2、删除session
                        sessionDao.delete(t);
        });
        return "redirect:/admin/guster/list";
    }

    /**
     * 分页查询所有登录用户信息，数据来源于shirosession
     * @param jqgridbean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sessions")
    public Map<String, Object> list(JqgridBean jqgridbean) throws Exception {
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();

        Example tuserExample = new Example(Tuser.class);
        Example.Criteria criteria = tuserExample.or();

        //当前实体类不包含名为userName的属性!
        if(StringUtils.isNotEmpty(jqgridbean.getSidx())&&StringUtils.isNotEmpty(jqgridbean.getSord())){
            tuserExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
        }

        PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
        List<Tuser> userList = tuserService.selectByExample(tuserExample);


        PageRusult<Tuser> pageRusult =new PageRusult<Tuser>(userList);

        resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
        resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
        resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
        resultmap.put("datamap", userList);
        return resultmap;
    }

}
