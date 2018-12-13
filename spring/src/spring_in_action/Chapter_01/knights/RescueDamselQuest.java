package spring_in_action.Chapter_01.knights;

/**
 * @ClassName: RescueDamselQuest
 * @author: ganfengbao
 * @Date: 2018/12/13 12:43
 */
public class RescueDamselQuest implements Quest{

    @Override
    public void embark() {
        System.out.println("Embarking on a quest to rescue the damsel.");
    }

}
