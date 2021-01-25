package com.jsw.web;

import com.github.pagehelper.PageHelper;
import com.jsw.entity.Tuser;
import com.jsw.entity.Twork;
import com.jsw.model.JqgridBean;
import com.jsw.model.PageRusult;
import com.jsw.service.TuserService;
import com.jsw.util.Collections3;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
     * 分页查询所有登录用户信息，数据来源于shirosession
     * @param jqgridbean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sessions")
    public Map<String, Object> list(JqgridBean jqgridbean) throws Exception {
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();


        //1、获取所有在线的用户名
        List<String> onlineUsers = Lists.newArrayList();
        Collection<Session> sessions = sessionDao.getActiveSessions();
        if(!Collections3.isEmpty(sessions)){
            sessions.stream().forEach(t->{
                onlineUsers.add((String) t.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            });
        }

        Example tuserExample = new Example(Tuser.class);
        Example.Criteria criteria = tuserExample
                .or()
                .andIn("userName", onlineUsers);
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
