package com.junbao.monitor;

import redis.clients.jedis.Jedis;

public class JedisMonitorThread extends Thread{
    private static JedisMonitorThread instance = new JedisMonitorThread();

    public static JedisMonitorThread getInstance() {
        return instance;
    }

    Jedis jedis = null;
    @Override
    public void run() {
        while (true) {
            try {
                jedis = new Jedis("39.108.54.243", 6379);
                jedis.brpop(0, "orderList");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
