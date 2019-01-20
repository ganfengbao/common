package com.junbao.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolDemo {
    public static void main(String[] args) {

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "39.108.54.243", 6379);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            System.out.println(jedis.get("hello"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                // 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
                jedis.close();
            }
        }
    }
}
