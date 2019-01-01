package nio.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: BufferDemo2
 * @author: ganfengbao
 * @Date: 2018/12/25 11:32
 */
public class BufferDemo2 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // �������е�����0-9
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        // ����ֻ��������
        ByteBuffer readonly = buffer.asReadOnlyBuffer();

        // �ı�ԭ������������
        for (int i = 0; i < buffer.capacity(); ++i) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readonly.position(0);
        readonly.limit(buffer.capacity());

        // ֻ��������������Ҳ��֮�ı�
        while (readonly.remaining() > 0) {
            System.out.println(readonly.get());
        }
    }
}
