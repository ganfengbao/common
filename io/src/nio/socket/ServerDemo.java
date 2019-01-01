package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @ClassName: ServerDemo
 * @author: ganfengbao
 * @Date: 2018/12/25 9:09
 */
public class ServerDemo {
    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;

    public ServerDemo() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("listening on port 8080");

        this.selector = Selector.open();
        ;

        // ��channel��accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws Exception {
        new ServerDemo().go();
    }

    private void go() throws Exception {

        // block api
        while (selector.select() > 0) {

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // ������
                if (selectionKey.isAcceptable()) {
                    System.out.println("isAcceptable");
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();

                    // ��ע��channel
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) {
                        continue;
                    }
                    socketChannel.configureBlocking(false);
                    // ע�⣡���������io������ǳ����ڱ������֮ǰ�ĵȴ������Ѿ������ע���¼����������ǾͿ����ڵȴ���ʱ����������飬
                    // ������������socket���ӣ�Ҳ����֮ǰ˵��һ���̼߳������socket���ӡ���Ҳ���ڱ����ʱ����ֱ�۵ĸ���
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);


                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    buffer.put("hi new channel".getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                }

                // ����˹��ĵĿɶ�����ζ�������ݴ�client�����ˣ����ݲ�ͬ����Ҫ���ж�ȡ��Ȼ�󷵻�
                if (selectionKey.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();

                    String receiveData = Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("receiveData:" + receiveData);

                    // �Ѷ��������ݰ󶨵�key��
                    selectionKey.attach("server message echo:" + receiveData);
                }

                // ʵ���Ϸ���˲�������������д��Ӧ����client�˹��ĵģ���ֻ�Ǹ�demo,˳����һ��selectionKey��attach����
                if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    String message = (String) selectionKey.attachment();
                    if (message == null) {
                        continue;
                    }
                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    while (writeBuffer.hasRemaining()) {
                        socketChannel.write(writeBuffer);
                    }
                }
            }
        }
    }
}
