package daugherty;


import com.daugherty.DaughertyHomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.slf4j.LoggerFactory.getLogger;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/context.xml"})
public class HomePageTest {

    private static final Logger logger = getLogger(HomePageTest.class);

    @Autowired
    private DaughertyHomePage daughertyHomePage;

    @Before
    public void setup() {
        daughertyHomePage.open();
    }

    @Test
    public void start() {
        daughertyHomePage.getAllMenu().forEach(menu -> {
            try {
                menu.click();
                String title = daughertyHomePage.getTitle();
                logger.info("clicking " + menu + " and title is \"" + title + "\"");
            } catch (Exception e) {
                logger.info("Error clicking " + menu, e);
            }
        });
    }

    @After
    public void close() {
        daughertyHomePage.close();
    }

}
