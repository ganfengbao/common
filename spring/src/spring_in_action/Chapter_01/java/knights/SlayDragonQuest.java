package spring_in_action.Chapter_01.java.knights;

import java.io.PrintStream;

/**
 * @ClassName: SlayDragonQuest
 * @author: ganfengbao
 * @Date: 2018/12/13 13:33
 */
public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
