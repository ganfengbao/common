package spring_in_action.Chapter_01.knights;

/**
 * @ClassName: BraveKnight
 * @author: ganfengbao
 * @Date: 2018/12/13 12:45
 */
public class BraveKnight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}