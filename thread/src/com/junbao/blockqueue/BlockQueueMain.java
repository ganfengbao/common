package com.junbao.blockqueue;

/**
 * @ClassName: BlockQueueMain
 * @author: ganfengbao
 * @Date: 2018/12/20 10:45
 */
public class BlockQueueMain {
    public static void main(String[] args) {
        BlockQueueEngine blockQueueEngine = new BlockQueueEngine();
        Message message = new Message();
        message.setTitle("gfb");
        blockQueueEngine.addQueueList(message);
        BlockQueueThread.getInstance().start();
        BlockQueueThread.getInstance().addQueueList(message);
    }
}
