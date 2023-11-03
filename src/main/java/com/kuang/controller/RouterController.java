package com.kuang.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/3 16:51
 */
@Controller
public class RouterController {
    @RequestMapping({"/", "/index"})
    public String index(){
        System.out.println("hello");
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "view/login";
    }

    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") String id){
        return "views/level1/" + id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") String id){
        return "views/level2/" + id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") String id){
        return "views/level3/" + id;
    }
}
