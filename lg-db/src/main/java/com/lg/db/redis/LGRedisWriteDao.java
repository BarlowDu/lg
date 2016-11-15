package com.lg.db.redis;

import com.alibaba.fastjson.JSON;
import com.lg.db.LGWriteDao;
import com.lg.model.PositionDetail;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by dutc on 2016/11/11.
 */
@Repository("redisWriteDao")
public class LGRedisWriteDao implements LGWriteDao {
    @Resource(name="jedis")
    Jedis jedis;
    public void WritePositionDetail(PositionDetail p) throws IOException {
        jedis.hset("lg_position",String.valueOf(p.getPositionId()), JSON.toJSONString(p));
    }


}
