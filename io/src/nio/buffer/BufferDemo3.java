package nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: BufferDemo3
 * @author: ganfengbao
 * @Date: 2018/12/25 11:33
 */
public class BufferDemo3 {
    public static void main(String[] args) throws IOException {

        String infile = "F:\\java\\idea\\common\\io\\src\\data\\nio-data.txt";
        FileInputStream fin = new FileInputStream(infile);
        FileChannel fcin = fin.getChannel();

        String outfile = String.format("F:\\java\\idea\\common\\io\\src\\data\\nio-write.txt");
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel fcout = fout.getChannel();

        // 使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }

    }
}
