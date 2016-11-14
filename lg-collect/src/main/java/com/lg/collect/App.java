package com.lg.collect;

import com.lg.service.LGWriteService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by dutc on 2016/11/11.
 */
@Component("app")
public class App {
    @Resource(name = "WriteService")
    private LGWriteService service;

    public static void main(String[] args) {
        /*//连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //存储数据到列表中
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // 获取存储的数据并输出

        List<String> list = jedis.lrange("tutorial-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }*/

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        App app = context.getBean("app", App.class);
        app.run();
        context.close();
    }

    private void run() {
        try {
            service.ReadAll();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }
}