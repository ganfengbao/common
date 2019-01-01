package nio.buffer;

import java.nio.IntBuffer;

/**
 * @ClassName: TestIntBuffer
 * @author: ganfengbao
 * @Date: 2018/12/25 11:22
 */
public class TestIntBuffer {
    public static void main(String[] args) {
        // �����µ�int������������Ϊ����������
        // �»������ĵ�ǰλ�ý�Ϊ�㣬�����(����λ��)��Ϊ����������������һ���ײ�ʵ�����飬������ƫ������Ϊ�㡣
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); ++i) {
            int j = 2 * (i + 1);
            // ����������д��˻������ĵ�ǰλ�ã���ǰλ�õ���
            buffer.put(j);
        }

        // ����˻�����������������Ϊ��ǰλ�ã�Ȼ�󽫵�ǰλ������Ϊ0
        buffer.flip();

        // �鿴�ڵ�ǰλ�ú�����λ��֮���Ƿ���Ԫ��
        while (buffer.hasRemaining()) {
            // ��ȡ�˻�������ǰλ�õ�������Ȼ��ǰλ�õ���
            int j = buffer.get();
            System.out.print(j + "  ");
        }
    }
}
