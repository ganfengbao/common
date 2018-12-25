package nio.channel.socketchannel.datachannel;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @ClassName: DatagramChannelClientDemo
 * @author: ganfengbao
 * @Date: 2018/12/25 11:07
 */
public class DatagramChannelClientDemo {
    // UDPЭ��ͻ���
    private String serverIp = "127.0.0.1";
    private int port = 9975;
    // private ServerSocketChannel serverSocketChannel;
    DatagramChannel channel;
    private Charset charset = Charset.forName("UTF-8");
    private Selector selector = null;

    public DatagramChannelClientDemo() throws IOException {
        try {
            selector = Selector.open();
            channel = DatagramChannel.open();
        } catch (Exception e) {
            selector = null;
            channel = null;
            System.out.println("��ʱ");
        }
        System.out.println("�ͻ�������");
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
        channel.connect(new InetSocketAddress(serverIp, port));// ���ӷ����
        channel.write(ByteBuffer.wrap(new String("�ͻ��������ȡ��Ϣ").getBytes()));
        channel.register(selector, SelectionKey.OP_READ);
        /** ��ѭ�����Ѿ�������SelectionKey��Ŀ */
        while (selector.select() > 0) {
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

    // /*
    // * ���� ��read()��IO
    // * */
    // synchronized public void reveice2(SelectionKey key) throws IOException {
    // if (key == null)
    // return;
    // // ***��channel.read()��ȡ��Ϣ***//
    // // ������ʱ��Ҫ�����ֽڳ���
    // DatagramChannel sc = (DatagramChannel) key.channel();
    // String content = "";
    // // create buffer with capacity of 48 bytes
    // ByteBuffer buf = ByteBuffer.allocate(3);// java��һ��(utf-8)����3�ֽ�,gbk����ռ2���ֽ�
    // int bytesRead = sc.read(buf); //read into buffer.
    //
    // while (bytesRead >0) {
    // buf.flip(); //make buffer ready for read
    // while(buf.hasRemaining()){
    // buf.get(new byte[buf.limit()]); // read 1 byte at a time
    // content += new String(buf.array());
    // }
    // buf.clear(); //make buffer ready for writing
    // bytesRead = sc.read(buf);
    // }
    // System.out.println("���գ�" + content);
    // }

    /* ���� */
    synchronized public void reveice(SelectionKey key) throws IOException {
        String threadName = Thread.currentThread().getName();
        if (key == null)
            return;
        try {
            // ***��channel.receive()��ȡ��Ϣ***//
            // ������ʱ��Ҫ�����ֽڳ���
            DatagramChannel sc = (DatagramChannel) key.channel();
            String content = "";
            // ��һ�νӣ�udp�������ݱ�ģʽ�����Ͷ��ٴΣ����ն��ٴ�
            ByteBuffer buf = ByteBuffer.allocate(65507);// java��һ��(utf-8)����3�ֽ�,gbk����ռ2���ֽ�
            buf.clear();
            SocketAddress address = sc.receive(buf); // read into buffer.
            String clientAddress = address.toString().replace("/", "").split(":")[0];
            String clientPost = address.toString().replace("/", "").split(":")[1];
            System.out.println(threadName + "\t" + address.toString());
            buf.flip(); // make buffer ready for read
            while (buf.hasRemaining()) {
                buf.get(new byte[buf.limit()]);// read 1 byte at a time
                byte[] tmp = buf.array();
                content += new String(tmp);
            }
            buf.clear(); // make buffer ready for writing��
            System.out.println(threadName + "���գ�" + content.trim());
            // �ڶ��ν�
            content = "";
            ByteBuffer buf2 = ByteBuffer.allocate(65507);// java��һ��(utf-8)����3�ֽ�,gbk����ռ2���ֽ�
            buf2.clear();
            SocketAddress address2 = sc.receive(buf2); // read into buffer.
            buf2.flip(); // make buffer ready for read
            while (buf2.hasRemaining()) {
                buf2.get(new byte[buf2.limit()]);// read 1 byte at a time
                byte[] tmp = buf2.array();
                content += new String(tmp);
            }
            buf2.clear(); // make buffer ready for writing��
            System.out.println(threadName + "����2��" + content.trim());

        } catch (PortUnreachableException ex) {
            System.out.println(threadName + "����˶˿�δ�ҵ�!");
        }
        send(2);
    }

    boolean flag = false;

    public void send(int i) {
        if (flag)
            return;
        try {
            // channel.write(ByteBuffer.wrap(new
            // String("�ͻ��������ȡ��Ϣ(��"+i+"��)").getBytes()));
            // channel.register(selector, SelectionKey.OP_READ );
            ByteBuffer buf2 = ByteBuffer.allocate(48);
            buf2.clear();
            buf2.put(("�ͻ��������ȡ��Ϣ(��" + i + "��)").getBytes());
            buf2.flip();
            channel.write(buf2);
            channel.register(selector, SelectionKey.OP_READ);
            // int bytesSent = channel.send(buf2, new
            // InetSocketAddress(serverIp,port)); // ����Ϣ���͸������
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
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
        new Thread(new Runnable() {
            public void run() {
                try {
                    new DatagramChannelClientDemo().service();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // new Thread(new Runnable() {
        // public void run() {
        // try {
        // new DatagramChannelClientDemo().service();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }).start();

    }
}
