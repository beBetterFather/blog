package com.jsw.web;

import com.google.common.collect.Maps;
import com.jsw.util.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/bb")
@Controller
public class WBBController {

    private static final Logger log = LoggerFactory.getLogger(WBBController.class);

    @RequestMapping("/images")
    @RequiresRoles(value = "superVIP")
    public String images(HttpServletRequest request, HttpSession httpSession){
        Map<String, String> images = Maps.newHashMap();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        ShiroHttpServletRequest  shiroHttpServletRequest = (ShiroHttpServletRequest) request;
        String age = StringUtils.join(shiroHttpServletRequest.getParameter("age"));

        Resource[] resources;
        try {
            switch (age){
                case "2011":
                    Resource[] resources2011 = resolver.getResources("classpath:static/wbb/2011/*.jpg");
                    resources = resources2011;
                    break;
                case "2012":
                    Resource[] resources2012 = resolver.getResources("classpath:static/wbb/2012/*.jpg");
                    resources = resources2012;
                    break;
                case "2013":
                    Resource[] resources2013 = resolver.getResources("classpath:static/wbb/2013/*.jpg");
                    resources = resources2013;
                    break;
                case "2014":
                    Resource[] resources2014 = resolver.getResources("classpath:static/wbb/2014/*.jpg");
                    resources = resources2014;
                    break;
                default:
                    Resource[] resourcesSweet = resolver.getResources("classpath:static/wbb/*.jpg");
                    resources = resourcesSweet;
            }
            // 合并路径
//            Resource[] resources = new Resource[resourcesSweet.length+resources2011.length+resources2012.length+resources2013.length+resources2014.length];
//            System.arraycopy(resourcesSweet, 0, resources, 0, resourcesSweet.length);
//            System.arraycopy(resources2011, 0, resources, resourcesSweet.length, resources2011.length);
//            System.arraycopy(resources2012, 0, resources, resourcesSweet.length+resources2011.length, resources2012.length);
//            System.arraycopy(resources2013, 0, resources, resourcesSweet.length+resources2011.length+resources2012.length, resources2013.length);
//            System.arraycopy(resources2014, 0, resources, resourcesSweet.length+resources2011.length+resources2012.length+resources2013.length, resources2014.length);

            // 遍历取出图片
            int i = 0;
            for(Resource resource: resources){
                images.put(StringUtils.join(i++), StringUtils.isNotEmpty(age)? StringUtils.join(age,"/\\", resource.getFilename()): resource.getFilename());
            }
        } catch (IOException e) {
            log.error("获取照片信息失败{}", e);
        }
        httpSession.setAttribute("images", images);
        return "/special/images";
    }
}
