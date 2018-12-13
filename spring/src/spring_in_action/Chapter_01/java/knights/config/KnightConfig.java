package spring_in_action.Chapter_01.java.knights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_in_action.Chapter_01.java.knights.BraveKnight;
import spring_in_action.Chapter_01.java.knights.Knight;
import spring_in_action.Chapter_01.java.knights.Quest;
import spring_in_action.Chapter_01.java.knights.SlayDragonQuest;

/**
 * Config ø…ª˘”⁄java≈‰÷√
 * @ClassName: KnightConfig
 * @author: ganfengbao
 * @Date: 2018/12/13 13:40
 */
@Configuration
public class KnightConfig {

    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }
}
