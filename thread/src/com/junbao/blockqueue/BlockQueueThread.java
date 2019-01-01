package com.junbao.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: BlockQueueThread
 * @author: ganfengbao
 * @Date: 2018/12/20 10:22
 */
public class BlockQueueThread extends Thread {

    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    private static BlockQueueThread instance = new BlockQueueThread();

    public static BlockQueueThread getInstance() {
        return instance;
    }

    public void addQueueList(Message messages) {
        try {
            queue.put(messages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        try {
            Message message = queue.take();
            System.out.println(message.getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
