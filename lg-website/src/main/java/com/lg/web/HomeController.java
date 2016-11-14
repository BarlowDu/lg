package com.lg.web;

import com.alibaba.fastjson.JSON;
import com.lg.model.PositionDetail;
import com.lg.service.LGReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by dutc on 2016/11/14.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Resource(name="reportService")
    LGReportService service;

    @RequestMapping("/index")
    public String Index(){
        System.out.print("index");
        return "index";
    }

    @RequestMapping("/edupie")
    public ModelAndView EduPie(){
        ModelAndView result=new ModelAndView("eduPie");
        List<PositionDetail> list=service.getAllPosition();
        Map<String,Integer> map=new HashMap<String, Integer>();
        for(PositionDetail p :list){
            if(map.containsKey(p.getEducation())){
                map.put(p.getEducation(),1);
            }else{
                map.put(p.getEducation(),map.get(p.getEducation())+1);
            }
        }
        Set<String> titles=map.keySet();
        List<Map<String,String>> data=new ArrayList<Map<String,String>>();
        for(Map.Entry<String,Integer> item :map.entrySet()){
            Map<String,String> m=new HashMap<String, String>();
            m.put("name",item.getKey());
            m.put("value",item.getValue().toString());
            data.add(m);
        }
        result.addObject("data", JSON.toJSONString(data));
        result.addObject("titles",JSON.toJSONString(titles));
        System.out.println(result.getViewName());
        System.out.println(result.getModel().get("titles"));
        return result;
    }
}
