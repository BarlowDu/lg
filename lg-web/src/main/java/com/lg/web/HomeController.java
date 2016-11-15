package com.lg.web;

import com.alibaba.fastjson.JSON;
import com.lg.model.PositionDetail;
import com.lg.service.LGReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String index(){
        System.out.print("index");
        return "index";
    }

    @RequestMapping("/chart")
    public ModelAndView chart(){
        ModelAndView result=new ModelAndView("chart");
        List<PositionDetail> list=service.getAllPosition();
        String[] titlesSalary=new String[]{"5","5-10","10-15","15-20","20-25","25"};
        int[] dataSalary=new int[]{0,0,0,0,0,0};
        Map<String,Integer> mapEdu=new HashMap<String, Integer>();

        for(PositionDetail p :list){
            if(!mapEdu.containsKey(p.getEducation())){
                mapEdu.put(p.getEducation(),1);
            }else{
                mapEdu.put(p.getEducation(),mapEdu.get(p.getEducation())+1);
            }
            if(p.getMinSalary()>0&&p.getMaxSalary()>0){
                if(p.getMaxSalary()<=5){
                    dataSalary[0]=dataSalary[0]+1;
                    continue;
                }
                if(p.getMinSalary()>25){
                    dataSalary[5]=dataSalary[0]+1;
                    continue;
                }
                if((p.getMinSalary()>5&&p.getMinSalary()<=10)||(p.getMaxSalary()<=10&&p.getMaxSalary()>5)){
                    dataSalary[1]=dataSalary[1]+1;
                }
                if((p.getMinSalary()>10&&p.getMinSalary()<=15)||(p.getMaxSalary()<=15&&p.getMaxSalary()>10)){
                    dataSalary[2]=dataSalary[2]+1;
                }
                if((p.getMinSalary()>15&&p.getMinSalary()<=20)||(p.getMaxSalary()<=20&&p.getMaxSalary()>15)){
                    dataSalary[3]=dataSalary[3]+1;
                }
                if((p.getMinSalary()>20&&p.getMinSalary()<=25)||(p.getMaxSalary()<=25&&p.getMaxSalary()>20)){
                    dataSalary[4]=dataSalary[5]+1;
                }
            }
        }
        Set<String> titlesEdu=mapEdu.keySet();
        List<Map<String,String>> dataEdu=new ArrayList<Map<String,String>>();
        for(Map.Entry<String,Integer> item :mapEdu.entrySet()){
            Map<String,String> m=new HashMap<String, String>();
            m.put("name",item.getKey());
            m.put("value",item.getValue().toString());
            dataEdu.add(m);
        }
        result.addObject("dataEdu", JSON.toJSONString(dataEdu));
        result.addObject("titlesEdu",JSON.toJSONString(titlesEdu));

        result.addObject("dataSalary", JSON.toJSONString(dataSalary));
        result.addObject("titlesSalary",JSON.toJSONString(titlesSalary));
        return result;
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<PositionDetail> all(){
        List<PositionDetail> list=service.getAllPosition();
        return list;
    }
}
