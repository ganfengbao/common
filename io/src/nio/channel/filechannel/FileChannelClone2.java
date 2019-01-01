package nio.channel.filechannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @ClassName: FileChannelClone
 * @author: ganfengbao
 * @Date: 2018/12/25 10:26
 */
public class FileChannelClone2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("F:\\java\\idea\\common\\io\\src\\data\\nio-data.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        RandomAccessFile bFile = new RandomAccessFile("F:\\java\\idea\\common\\io\\src\\data\\nio-write.txt", "rw");
        FileChannel toChannel = bFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
        aFile.close();
        bFile.close();
        System.out.println("over!");
    }
}
