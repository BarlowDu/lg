package com.lg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/11/13.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    public String Index(){
        return "lg";
    }
}
