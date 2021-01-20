package com.jsw.web;

import com.github.pagehelper.PageHelper;
import com.jsw.entity.Trole;
import com.jsw.entity.Tuser;
import com.jsw.entity.Twork;
import com.jsw.model.JqgridBean;
import com.jsw.model.PageRusult;
import com.jsw.service.TworkService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/nari")
public class NariController {

    @Resource
    private TworkService workService;

    @RequestMapping("/work")
    public String work(){
        return "/nari/userInfo";
    }

    @RequestMapping("/statistics")
    public String statistics(){
        return "/nari/userFlot";
    }

    /**
     * 分页查询工作信息
     */
    @ResponseBody
    @RequestMapping(value = "/list")
//    @RequiresPermissions(value = {"用户管理"})
    public Map<String, Object> list(JqgridBean jqgridbean) throws Exception {
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();

        Example tworkExample = new Example(Twork.class);
//        Example.Criteria criteria = tworkExample.or();
//        criteria.andNotEqualTo("userName","admin");
        //当前实体类不包含名为userName的属性!

        if(StringUtils.isNotEmpty(jqgridbean.getSidx())&&StringUtils.isNotEmpty(jqgridbean.getSord())){
            tworkExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
        }

        PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
        List<Twork> workList = workService.selectByExample(tworkExample);
        PageRusult<Twork> pageRusult =new PageRusult<Twork>(workList);

        resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
        resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
        resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
        resultmap.put("datamap", workList);

        return resultmap;
    }

    @ResponseBody
    @RequestMapping(value = "/addupdatework")
//    @RequiresPermissions(value = {"用户管理"})
    public Map<String, Object> addupdatework(Twork twork) {
            LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
        try {
            if (twork.getId() == null) {//新建
                //首先判断用户名是否可用
//                Example tworkExample = new Example(Twork.class);
                /*List<Twork> worklist = workService.selectByExample(tworkExample);
                if (worklist != null && worklist.size() > 0) {
                    resultmap.put("state", "fail");
                    resultmap.put("mesg", "当前考勤记录已存在");
                    return resultmap;
                }*/
                workService.saveNotNull(twork);
            } else {//编辑
                Twork oldObject = workService.selectByKey(twork.getId());
                if(oldObject==null){
                    resultmap.put("state", "fail");
                    resultmap.put("mesg", "当前考勤记录不存在");
                    return resultmap;
                }else{
                    workService.updateNotNull(twork);
                }
            }
            resultmap.put("state", "success");
            resultmap.put("mesg", "操作成功");
            return resultmap;
        } catch (Exception e) {
            e.printStackTrace();
            resultmap.put("state", "fail");
            resultmap.put("mesg", "操作失败，系统异常");
            return resultmap;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectWorkById")
    public Map<String, Object> selectUserById(Twork twork) {
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
        try {
            if(twork.getId()!=null&&!twork.getId().equals(0)){
                twork = workService.selectByKey(twork.getId());
                if(twork==null){
                    resultmap.put("state", "fail");
                    resultmap.put("mesg", "无法找到该记录");
                    return resultmap;
                }
            }else{
                resultmap.put("state", "fail");
                resultmap.put("mesg", "无法找到该记录的id");
                return resultmap;
            }


            resultmap.put("twork", twork);
            resultmap.put("state", "success");
            resultmap.put("mesg", "获取成功");
            return resultmap;
        } catch (Exception e) {
            e.printStackTrace();
            resultmap.put("state", "fail");
            resultmap.put("mesg", "获取失败，系统异常");
            return resultmap;
        }
    }

    @RequestMapping(value = "/updateOverTime")
    @ResponseBody
    public void updateOverTime(){
        workService.updateOverTime();
    }

}
