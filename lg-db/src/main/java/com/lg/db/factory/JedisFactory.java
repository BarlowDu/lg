package com.lg.db.factory;


import redis.clients.jedis.*;



/**
 * Created by dutc on 2016/11/11.
 */
public class JedisFactory {
    public JedisPool pool;

    public JedisFactory() {

        JedisPoolConfig config = new JedisPoolConfig();
         //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
         //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。

         //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
         config.setMaxIdle(5);
         //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；

         //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
         config.setTestOnBorrow(true);


        pool = new JedisPool(config,"localhost",6379);

    }

    public Jedis getJedis() {
        return pool.getResource();
    }
}
