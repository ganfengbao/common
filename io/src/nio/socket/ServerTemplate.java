package nio.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: ServerTemplate
 * @author: ganfengbao
 * @Date: 2018/12/25 9:42
 */
public class ServerTemplate {
    public static void main(String[] args) throws IOException {

        new Thread(new ReactorTask()).start();


    }

    public static class ReactorTask implements Runnable {

        private Selector selector;

        public ReactorTask() {
            try {
                // ��һ������ServerSocketChannel�����ڼ����ͻ��˵����ӣ��������пͻ������ӵĸ��ܵ�
                ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

                // �ڶ��������������˿ڣ���������Ϊ������ģʽ
                acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), 1234));
                acceptorSvr.configureBlocking(false);

                // ������������Reactor�̣߳�������·�������������߳�
                selector = Selector.open();

                // ���Ĳ�����ServerSocketChannelע�ᵽReactor�̵߳Ķ�·������Selector�ϣ�����Accept�¼�
                SelectionKey key = acceptorSvr.register(selector, SelectionKey.OP_ACCEPT);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // ���岽����run����������ѭ��������ѯ׼��������Key
            while (true) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectedKeys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        try {
                            if (key.isValid()) {
                                // �����½����������Ϣ
                                if (key.isAcceptable()) {
                                    // ����������·���������������µĿͻ��˽��룬�����µĽ����������TCP�������֣�����������·
                                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                    SocketChannel sc = ssc.accept();
                                    // ���߲������ÿͻ�����·Ϊ������ģʽ
                                    sc.configureBlocking(false);
                                    sc.socket().setReuseAddress(true);
                                    // �ڰ˲������½���Ŀͻ�������ע�ᵽReactor�̵߳Ķ�·�������ϣ���������������ȡ�ͻ��˷��͵�������Ϣ
                                    sc.register(selector, SelectionKey.OP_READ);
                                }
                                if (key.isReadable()) {
                                    // �ھŲ����첽��ȡ�ͻ���������Ϣ��������
                                    SocketChannel sc = (SocketChannel) key.channel();
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                    int readBytes = sc.read(readBuffer);

                                    // ��ʮ������ByteBuffer���б���룬����а����Ϣָ��reset��������ȡ�����ı���
                                    if (readBytes > 0) {
                                        readBuffer.flip();
                                        byte[] bytes = new byte[readBuffer.remaining()];
                                        readBuffer.get(bytes);
                                        String body = new String(bytes, "UTF-8");
                                        System.out.println("The time server receive order : " + body);
                                        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                                                ? new java.util.Date(System.currentTimeMillis()).toString()
                                                : "BAD ORDER";
                                        //дӦ��
                                        byte[] bytes2 = currentTime.getBytes();
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes2.length);
                                        writeBuffer.put(bytes2);
                                        writeBuffer.flip();
                                        sc.write(writeBuffer);
                                    } else if (readBytes < 0) {
                                        // �Զ���·�ر�
                                        key.cancel();
                                        sc.close();
                                    } else
                                        ; // ����0�ֽڣ�����
                                }
                            }
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null)
                                    key.channel().close();
                            }
                        }
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

        }
    }
}
