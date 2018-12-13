package spring_in_action.Chapter_01.java.knights;

/**
 * @ClassName: DamselRescuingKnight
 * @author: ganfengbao
 * @Date: 2018/12/13 12:43
 */
public class DamselRescuingKnight implements Knight{

    private RescueDamselQuest quest;

    public DamselRescuingKnight() {
        this.quest = new RescueDamselQuest();
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
