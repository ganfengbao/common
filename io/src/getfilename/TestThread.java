package getfilename;

/**
 * @Author: ganfengbao
 * @Date: 2018/12/21 0:06
 * @Version 1.0
 */
public class TestThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(System.currentTimeMillis());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
