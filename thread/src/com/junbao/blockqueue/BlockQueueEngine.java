package com.junbao.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: BlockQueueEngine
 * @author: ganfengbao
 * @Date: 2018/12/20 10:20
 */
public class BlockQueueEngine {
    private BlockingQueue<Message> queue = new LinkedBlockingQueue();


    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void addQueueList(Message messages) {
        try {
            queue.put(messages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
