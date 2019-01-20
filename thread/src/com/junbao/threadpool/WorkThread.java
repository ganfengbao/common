package com.junbao.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

public class WorkThread implements Runnable {
    private static LinkedBlockingQueue<Work> queue = new LinkedBlockingQueue();

    @Override
    public void run() {
        while (true) {
            try {
                Work work = queue.take();
                System.out.println(work.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void putOffer(Work work) {
       queue.offer(work);
    }

}
