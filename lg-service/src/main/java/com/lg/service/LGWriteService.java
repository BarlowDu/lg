package com.lg.service;

import com.alibaba.fastjson.JSON;
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
public class LGWriteService {
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
        int total = getTotalPoisiton(params);
        System.out.println(total);
        int page=total/pz+(total%pz==0?0:1);
        Map<String,String> headers=new HashMap<String,String>();

        for(int i=1;i<=page;i++){
            System.out.println(i);
            params.put("pn",i);
            LagouModel model = CustomerWebResource.post(url, params,headers, LagouModel.class);
            if(model==null){
                continue;
            }
            for(PositionDetail item:model.getContent().getPositionResult().getResult()){
                //PositionDetail item=p.getPosition();
                handler.handle(item);
                positionId.add(item.getPositionId());
                pubId.add(item.getPublisherId());
                System.out.println(item.getCompanyFullName()+"\t"+item.getPositionName());
                dao.WritePositionDetail(item);
            }

            TimeUnit.MILLISECONDS.sleep(1000*10);
        }
        System.out.println("positionId:"+positionId.size());
        System.out.println("pubId:"+pubId.size());
        System.out.println("over...");

    }

    private int getTotalPoisiton(Map<String, Object> params) throws IOException {
        LagouModel model = CustomerWebResource.post(url, params,new HashMap<String, String>(), LagouModel.class);

        return model.getContent().getPositionResult().getTotalCount();
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
