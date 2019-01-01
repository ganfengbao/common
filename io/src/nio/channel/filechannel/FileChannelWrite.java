package nio.channel.filechannel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: FileChannelWrite
 * @author: ganfengbao
 * @Date: 2018/12/25 10:22
 */
public class FileChannelWrite {
    static private final byte message[] = { 83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46 };

    static public void main(String args[]) throws Exception {
        FileOutputStream fout = new FileOutputStream("F:\\java\\idea\\common\\io\\src\\data\\nio-write.txt");
        FileChannel fc = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }
        buffer.flip();
        fc.write(buffer);
        fout.close();
    }
}
