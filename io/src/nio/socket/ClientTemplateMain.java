package nio.socket;

/**
 * @ClassName: ClientTemplateMain
 * @author: ganfengbao
 * @Date: 2018/12/25 9:58
 */
public class ClientTemplateMain {
    public static void main(String[] args) {

        int port = 1234;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new Thread(new ClientTemplate("127.0.0.1", port), "TimeClient-001")
                .start();
    }
}
