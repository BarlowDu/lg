package com.lg.service;

import com.lg.db.LGReportDao;
import com.lg.db.LGWriteDao;
import com.lg.model.LagouModel;
import com.lg.model.PositionDetail;
import com.lg.model.PositionResult;
import com.lg.model.handler.PositionDetailHandler;
import com.lg.utils.net.CustomerWebResource;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by dutc on 2016/11/9.
 */
@Service("WriteService")
public class LGElasticWriteService {
    @Resource(name="redisWriteDao")
    private LGWriteDao dao;

    @Resource(name="redisReportDao")
    private LGReportDao reportDao;

    @Resource(name="detailHandler")
    private PositionDetailHandler handler;


    String url = "https://www.lagou.com/jobs/positionAjax.json?city=北京&needAddtionalResult=false";
    public void run() throws IOException, InterruptedException {
        Set<Integer> positionId=new HashSet<Integer>();
        Set<Integer> pubId=new HashSet<Integer>();
        int pz=15;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("first", false);
        params.put("kd", "java");
        params.put("pn", 1);
        int total = 300;//getTotalPoisiton(params);
        System.out.println(total);
        int page=total/pz+(total%pz==0?0:1);
        Map<String,String> headers=new HashMap<String,String>();
        headers.put("Cookie","LGMOID=20161104154143-11D95FD393E15BF661E646C58784554C; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1478749479,1478840016,1478843333,1478844040; _ga=GA1.2.1317494082.1478245217; user_trace_token=20161104154145-22c3df9e-a262-11e6-8ad6-525400f775ce; LGUID=20161104154145-22c3e51f-a262-11e6-8ad6-525400f775ce; index_location_city=%E5%8C%97%E4%BA%AC; SEARCH_ID=9f638054055847aa876b9aefb610df57; HISTORY_POSITION=2118387%2C4k-8k%2C%E4%B8%96%E7%BA%AA%E9%87%91%E6%96%87%2C%E8%BF%90%E8%90%A5%E4%B8%93%E5%91%98%7C2168446%2C10k-15k%2C%E4%B8%9C%E6%96%B9%E5%8A%A0%E6%85%A7%2CGolang%E9%AB%98%E7%BA%A7%E5%BC%80%E5%8F%91%7C1599849%2C10k-15k%2C%E5%A7%BF%E6%9C%AC%E6%B1%87%2C%E9%AB%98%E7%BA%A7PHP%E5%B7%A5%E7%A8%8B%E5%B8%88%7C1996673%2C6k-8k%2C%E4%B8%89%E5%8D%81%E5%85%AB%E5%BA%A6%E4%BA%94%2CPHP%7C2386102%2C15k-25k%2C%E4%B8%80%E7%82%B9%E8%B5%84%E8%AE%AF%2C%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90%E5%B8%88%7C; LGSID=20161111134938-a22fb4bb-a7d2-11e6-9fcf-525400f775ce; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; JSESSIONID=E7D5087999DE61DAD28B4ADE6A4D3590; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1478844293; LGRID=20161111140538-de6eb9df-a7d4-11e6-9fd0-525400f775ce; TG-TRACK-CODE=index_search");
        headers.put("Host","www.lagou.com");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
        headers.put("Accept","application/json, text/javascript, */*; q=0.01");
        headers.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        headers.put("Accept-Encoding","gzip, deflate, br");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("X-Requested-With","XMLHttpRequest");
        headers.put("X-Anit-Forge-Token","None");
        headers.put("X-Anit-Forge-Code","0");
        headers.put("Referer","https://www.lagou.com/jobs/list_java?city=%E5%8C%97%E4%BA%AC&cl=false&fromSearch=true&labelWords=&suginput=");
        headers.put("Conent-Length","23");
        headers.put("Connection","keep-alive");
        for(int i=1;i<=page;i++){
            System.out.println(i);
            params.put("pn",i);

            LagouModel model = CustomerWebResource.post(url, params,headers, LagouModel.class);
            if(model==null){
                continue;
            }
            for(PositionResult p:model.getContent().getPositionResult()){
                PositionDetail item=p.getPosition();
                handler.handle(item);
                positionId.add(item.getPositionId());
                pubId.add(item.getPublisherId());
                System.out.println(item.getCompanyFullName()+"\t"+item.getPositionName());
                //dao.WritePositionDetail(item);
            }

            TimeUnit.MILLISECONDS.sleep(1000*40);
        }
        System.out.println("positionId:"+positionId.size());
        System.out.println("pubId:"+pubId.size());
        System.out.println("over...");

    }

    private int getTotalPoisiton(Map<String, Object> params) throws IOException {
        LagouModel model = CustomerWebResource.post(url, params,null, LagouModel.class);

        return model.getContent().getTotalCount();
    }

    public void ReadAll(){
        List<PositionDetail> positions=reportDao.GetAllPosition();
        Set<String> salary=new HashSet<String>();
        Set<String> workYear=new HashSet<String>();
        Set<String> edu=new HashSet<String>();
        for(PositionDetail p :positions){
            System.out.println(p.getCompanyFullName()+"\t"+p.getPositionName());
            salary.add(p.getSalary());
            workYear.add(p.getWorkYear());
            edu.add(p.getEducation());
        }
        System.out.println("salary:");
        System.out.println(salary);
        System.out.println("workYear:");
        System.out.println(workYear);
        System.out.println("edu:");
        System.out.println(edu);
    }
}
