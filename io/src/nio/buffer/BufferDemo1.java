package nio.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: BufferDemo1
 * @author: ganfengbao
 * @Date: 2018/12/25 11:30
 */
public class BufferDemo1 {
    static public void main(String args[]) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // �������е�����0-9
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        // �����ӻ�����
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        // �ı��ӻ�����������
        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}
