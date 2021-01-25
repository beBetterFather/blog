package com.jsw.web;

import com.jsw.entity.Tuser;
import com.jsw.service.TuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/guster")
public class GusterController {

    @Resource
    private TuserService tuserService;

    @RequestMapping("/list")
    public String list(HttpSession httpSession){
        Example example = new Example(Tuser.class);
        example.or().andNotEqualTo("userName", "admin");
        List<Tuser> users = tuserService.selectByExample(example);
        httpSession.setAttribute("users", users);
        return "/guster/contacts";
    }

}
