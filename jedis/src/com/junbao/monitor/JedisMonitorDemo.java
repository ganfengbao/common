package com.junbao.monitor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

import java.util.HashSet;
import java.util.Set;

public class JedisMonitorDemo {
    private static int IPNumber = 0;
    public static void main(String[] args) {

        Jedis jedis = null;
        try {
            jedis = new Jedis("39.108.54.243", 6379);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        HedisMonitorThread.getInstance().start();
        JedisMonitorThread.getInstance().start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Jedis jedisMonitor = new Jedis("39.108.54.243");
//                for (int i = 0; i < 100; i++) {
//                    jedisMonitor.set("root", String.valueOf(i));
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                jedisMonitor.disconnect();
//            }
//        }).start();

        Set<String> hashSet = new HashSet<String>();

        jedis.monitor(new JedisMonitor() {
            @Override
            public void onCommand(String command) {
                command = command.substring(20);

                String[] str1 = command.split("\\:");

                String IP = str1[0];

                String[] str2 = str1[1].split("\"");

                String hander = str2[1];

                String key = str2[3];

                String value = str2[5];

                if (!hashSet.add(IP + " " + hander + " " + key + " " + value)) {
                    IPNumber++;
                }

                for (String str : hashSet) {
                    System.out.println(str);
                }

            }
        });
    }
}
