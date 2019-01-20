package com.junbao.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

public class TravelThread implements Runnable{

    private static LinkedBlockingQueue<Travel> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Travel travel = queue.take();
                System.out.println(travel.getNumber());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void putOffer(Travel travel) {
        queue.offer(travel);
    }
}
