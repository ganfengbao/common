package nio.channel.filechannel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: FileChannelRead
 * @author: ganfengbao
 * @Date: 2018/12/25 10:19
 */
public class FileChannelRead {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("F:\\java\\idea\\common\\io\\src\\data\\nio-data.txt");
        // 获取通道
        FileChannel fc = fin.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据到缓冲区
        fc.read(buffer);
        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print(((char) b));
        }
        fin.close();
    }
}
