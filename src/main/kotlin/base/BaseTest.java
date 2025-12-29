package base;

import driver.PlaywrightManager;
import utils.ScreenshotUtil;
import org.junit.jupiter.api.*;

public abstract class BaseTest {

    @BeforeEach
    void setUp() {
        PlaywrightManager.init();
    }

    @AfterEach
    void tearDown(TestInfo info) {
        try {
            ScreenshotUtil.take(PlaywrightManager.page(), info.getDisplayName().replace(" ", "_"));
        } catch (Exception ignored) {}

        PlaywrightManager.close();
    }
}
