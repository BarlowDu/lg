package com.lg.db.redis;

import com.alibaba.fastjson.JSON;
import com.lg.db.LGReportDao;
import com.lg.model.PositionDetail;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by dutc on 2016/11/11.
 */
@Repository("redisReportDao")
public class LGRedisReportDao implements LGReportDao {
    @Resource(name="jedis")
    Jedis jedis;

    public List<PositionDetail> GetAllPosition(){
        List<PositionDetail> result=new ArrayList<PositionDetail>();
        Map<String,String> map=jedis.hgetAll("lg_position");
        for(Map.Entry<String,String> entry:map.entrySet()){
            result.add(JSON.parseObject(entry.getValue(),PositionDetail.class));
        }
        return result;
    }
}
