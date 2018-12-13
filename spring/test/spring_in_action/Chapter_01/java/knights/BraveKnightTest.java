package spring_in_action.Chapter_01.java.knights;

import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * @ClassName: BraveKnightTest
 * @author: ganfengbao
 * @Date: 2018/12/13 12:48
 */
public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest() {
        Quest mockQuest = mock(Quest.class);
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }
}
