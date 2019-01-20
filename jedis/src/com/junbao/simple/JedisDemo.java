package com.junbao.simple;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    public static void main(String[] args) {
        /*普通连接*/
//        Jedis jedis = new Jedis("39.108.54.243", 6379);
//
//        jedis.set("hello", "world");
//
//        System.out.println(jedis.get("hello"));

        /*容错连接*/
        Jedis jedis = null;
        try {
            jedis = new Jedis("39.108.54.243", 6379);

//            1.String
            jedis.set("hello", "word");
            System.out.println(jedis.get("hello"));

            //2.hash
            jedis.hset("myhash", "hash1", "hv1");
            System.out.println(jedis.hget("myhash", "hash1"));

            //3.list
            jedis.lpush("list", "a", "b");
            System.out.println(jedis.brpop(0, "list"));

            //4.set
            jedis.sadd("myset", "a", "123");
            System.out.println(jedis.smembers("myset"));

            //5.zset
            jedis.zadd("myzset", 99, "tom");
            System.out.println(jedis.zcard("myzset"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
}
