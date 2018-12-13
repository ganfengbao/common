package spring_in_action.Chapter_01.java.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: KnightMain
 * @author: ganfengbao
 * @Date: 2018/12/13 13:45
 */
public class KnightMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                        "minstrel.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
