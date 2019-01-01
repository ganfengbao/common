package nio.channel.socketchannel.datachannel;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @ClassName: DatagramChannelServerDemo
 * @author: ganfengbao
 * @Date: 2018/12/25 11:06
 */
public class DatagramChannelServerDemo {
    // UDPЭ������
    private int port = 9975;
    DatagramChannel channel;
    private Charset charset = Charset.forName("UTF-8");
    private Selector selector = null;

    public DatagramChannelServerDemo() throws IOException {
        try {
            selector = Selector.open();
            channel = DatagramChannel.open();
        } catch (Exception e) {
            selector = null;
            channel = null;
            System.out.println("��ʱ");
        }
        System.out.println("����������");
    }

    /* ������� */
    public ByteBuffer encode(String str) {
        return charset.encode(str);
    }

    /* ������� */
    public String decode(ByteBuffer bb) {
        return charset.decode(bb).toString();
    }

    /* ���������񷽷� */
    public void service() throws IOException {
        if (channel == null || selector == null)
            return;
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        // channel.write(ByteBuffer.wrap(new String("aaaa").getBytes()));
        channel.register(selector, SelectionKey.OP_READ);
        /** ��ѭ�����Ѿ�������SelectionKey��Ŀ */
        while (selector.select() > 0) {
            System.out.println("����channel����");
            /* �õ��Ѿ��������˵�SelectionKey�ļ��� */
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) iterator.next();
                    iterator.remove();

                    if (key.isReadable()) {
                        reveice(key);
                    }
                    if (key.isWritable()) {
                        // send(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            key.cancel();
                            key.channel().close();
                        }
                    } catch (ClosedChannelException cex) {
                        e.printStackTrace();
                    }
                }
            }
            /* ��ѭ���� */
        }
        /* ��ѭ���� */
    }

    /*
     * ���� ��receive()��IO ��Ϊ�����һ�㲻��Ҫ����connect()�����δ����<span style=
     * "font-family: Arial, Helvetica, sans-serif;">connect()ʱ��</span><span
     * style="font-family: Arial, Helvetica, sans-serif;"
     * >��read()\write()��д���ᱨjava.nio.channels</span> .NotYetConnectedException
     * ֻ�е���connect()֮��,����ʹ��read��write.
     */
    synchronized public void reveice(SelectionKey key) throws IOException {
        if (key == null)
            return;
        // ***��channel.receive()��ȡ�ͻ�����Ϣ***//
        // ������ʱ��Ҫ�����ֽڳ���
        DatagramChannel sc = (DatagramChannel) key.channel();
        String content = "";
        // create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(1024);// java��һ��(utf-8)����3�ֽ�,gbk����ռ2���ֽ�
        buf.clear();
        SocketAddress address = sc.receive(buf); // read into buffer. ���ؿͻ��˵ĵ�ַ��Ϣ
        String clientAddress = address.toString().replace("/", "").split(":")[0];
        String clientPost = address.toString().replace("/", "").split(":")[1];

        buf.flip(); // make buffer ready for read
        while (buf.hasRemaining()) {
            buf.get(new byte[buf.limit()]);// read 1 byte at a time
            content += new String(buf.array());
        }
        buf.clear(); // make buffer ready for writing
        System.out.println("���գ�" + content.trim());
        // ��һ�η���udp�������ݱ�ģʽ�����Ͷ��ٴΣ����ն��ٴ�
        ByteBuffer buf2 = ByteBuffer.allocate(65507);
        buf2.clear();
        buf2.put(
                "��Ϣ�������� abc..UDP��һ�������ӵ�Э�飬��������֮ǰԴ�˺��ն˲��������ӣ������봫��ʱ�ͼ򵥵�ȥץȡ����Ӧ�ó�������ݣ��������ܿ�ذ����ӵ������ϡ��ڷ��Ͷ�UDP��һ�������ӵ�Э�飬��������֮ǰԴ�˺��ն˲��������ӣ������봫��ʱ�ͼ򵥵�ȥץȡ����Ӧ�ó�������ݣ��������ܿ�ذ����ӵ������ϡ��ڷ��Ͷ�UDP��һ�������ӵ�Э�飬��������֮ǰԴ�˺��ն˲��������ӣ������봫��ʱ�ͼ򵥵�ȥץȡ����Ӧ�ó�������ݣ��������ܿ�ذ����ӵ������ϡ��ڷ��Ͷ�@Q"
                        .getBytes());
        buf2.flip();
        channel.send(buf2, new InetSocketAddress(clientAddress, Integer.parseInt(clientPost))); // ����Ϣ���͸��ͻ���

        // �ڶ��η�
        ByteBuffer buf3 = ByteBuffer.allocate(65507);
        buf3.clear();
        buf3.put("�������".getBytes());
        buf3.flip();
        channel.send(buf3, new InetSocketAddress(clientAddress, Integer.parseInt(clientPost))); // ����Ϣ���͸��ͻ���
    }

    int y = 0;

    public void send(SelectionKey key) {
        if (key == null)
            return;
        // ByteBuffer buff = (ByteBuffer) key.attachment();
        DatagramChannel sc = (DatagramChannel) key.channel();
        try {
            sc.write(ByteBuffer.wrap(new String("aaaa").getBytes()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("send2() " + (++y));
    }

    /* �����ļ� */
    public void sendFile(SelectionKey key) {
        if (key == null)
            return;
        ByteBuffer buff = (ByteBuffer) key.attachment();
        SocketChannel sc = (SocketChannel) key.channel();
        String data = decode(buff);
        if (data.indexOf("get") == -1)
            return;
        String subStr = data.substring(data.indexOf(" "), data.length());
        System.out.println("��ȡ֮����ַ����� " + subStr);
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(subStr);
            FileChannel fileChannel = fileInput.getChannel();
            fileChannel.transferTo(0, fileChannel.size(), sc);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new DatagramChannelServerDemo().service();
    }
}
