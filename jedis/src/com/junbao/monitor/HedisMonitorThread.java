package com.junbao.monitor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

public class HedisMonitorThread extends Thread {
    private static HedisMonitorThread instance = new HedisMonitorThread();

    public static HedisMonitorThread getInstance() {
        return instance;
    }

    Jedis jedis = null;
    @Override
    public void run() {

        while (true) {
            try {
                jedis = new Jedis("39.108.54.243", 6379);
                jedis.lpush("orderList", "sdsd");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
