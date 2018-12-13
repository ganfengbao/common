package spring_in_action.Chapter_01.java.knights;

import java.io.PrintStream;

/**
 * @ClassName: Minstrel
 * @author: ganfengbao
 * @Date: 2018/12/13 18:52
 */
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la, the knight is so brave!");
    }

    public void singAfterQuest() {
        stream.println("Tee hee hee, the brave knight " +
                "did embark on a quest!");
    }
}
