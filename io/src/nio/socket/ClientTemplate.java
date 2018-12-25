package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: ClientTemplate
 * @author: ganfengbao
 * @Date: 2018/12/25 9:56
 */
public class ClientTemplate implements Runnable {
    private String host;
    private int port;

    private Selector selector;
    private SocketChannel socketChannel;

    private volatile boolean stop;

    public ClientTemplate(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            //��һ������SocketChannel,���ڴ����ͻ�������
            socketChannel = SocketChannel.open();
            //�ڶ���������SocketChannelΪ������ģʽ
            socketChannel.configureBlocking(false);
            //��������������·����������Reactor�߳��У�
            selector = Selector.open();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            // ���Ĳ���socketChannel��������
            if (socketChannel.connect(new InetSocketAddress(host, port))) {
                //���岽�����ֱ�����ӳɹ�����ע�ᵽ��·��������
                socketChannel.register(selector, SelectionKey.OP_READ);
                //������������������Ϣ����Ӧ��
                byte[] req = "QUERY TIME ORDER".getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                writeBuffer.put(req);
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
                if (!writeBuffer.hasRemaining())
                    System.out.println("Send order 2 server succeed.");
            } else
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                //���߲�����·��������run������ѭ��������ѯ׼��������Key
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        if (key.isValid()) {
                            //�ڰ˲��������ӳɹ���Channelע�ᵽ��·��������
                            // �ж��Ƿ����ӳɹ�
                            SocketChannel sc = (SocketChannel) key.channel();
                            if (key.isConnectable()) {
                                if (sc.finishConnect()) {
                                    sc.register(selector, SelectionKey.OP_READ);
                                    //����������Ϣ����Ӧ��
                                    byte[] req = "QUERY TIME ORDER".getBytes();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                                    writeBuffer.put(req);
                                    writeBuffer.flip();
                                    sc.write(writeBuffer);
                                    if (!writeBuffer.hasRemaining())
                                        System.out.println("Send order 2 server succeed.");
                                } else
                                    System.exit(1);// ����ʧ�ܣ������˳�
                            }
                            //��������������ȡ�����д�ص�������Ϣ
                            if (key.isReadable()) {
                                //�ھŲ�����ȡ��Ϣ��������
                                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                int readBytes = sc.read(readBuffer);
                                if (readBytes > 0) {
                                    readBuffer.flip();
                                    byte[] bytes = new byte[readBuffer.remaining()];
                                    readBuffer.get(bytes);
                                    String body = new String(bytes, "UTF-8");
                                    System.out.println("Now is : " + body);
                                    this.stop = true;
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
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        // ��·�������رպ�����ע���������Channel��Pipe����Դ���ᱻ�Զ�ȥע�Ტ�رգ����Բ���Ҫ�ظ��ͷ���Դ
        if (selector != null)
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
