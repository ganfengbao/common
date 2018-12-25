package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @ClassName: ServerSocketChannelTest
 * @author: ganfengbao
 * @Date: 2018/12/25 13:21
 */
public class ServerSocketChannelTest {
    private int size = 1024;
    private ServerSocketChannel socketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private final int port = 8998;
    private int remoteClientNum=0;

    public ServerSocketChannelTest() {
        try {
            initChannel();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void initChannel() throws Exception {
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(port));
        System.out.println("listener on port:" + port);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocateDirect(size);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    private void listener() throws Exception {
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //a connection was accepted by a ServerSocketChannel.
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    remoteClientNum++;
                    System.out.println("online client num="+remoteClientNum);
                    replyClient(channel);
                }
                //a channel is ready for reading
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                ite.remove();//must
            }

        }
    }

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip(); // Make buffer readable
            // Send the data; don't assume it goes all at once
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear(); // Empty buffer
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void replyClient(SocketChannel channel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("hello client!\r\n".getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int ops) throws Exception {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }


    public static void main(String[] args) {
        try {
            new ServerSocketChannelTest().listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
