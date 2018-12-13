package spring_in_action.Chapter_01.knights;

/**
 * @ClassName: DamselRescuingKnight
 * @author: ganfengbao
 * @Date: 2018/12/13 12:43
 */
public class DamselRescuingKnight {

    private RescueDamselQuest quest;

    public DamselRescuingKnight() {
        this.quest = new RescueDamselQuest();
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
