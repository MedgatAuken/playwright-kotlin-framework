package base

import driver.PlaywrightManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import utils.ScreenshotUtil

abstract class BaseTest {

    @BeforeEach
    fun setUp() {
        PlaywrightManager.init()
    }

    @AfterEach
    fun tearDown(info: TestInfo) {
        try {
            ScreenshotUtil.take(PlaywrightManager.getPage(), info.displayName.replace(" ", "_"))
        } catch (_: Exception) {
        }

        PlaywrightManager.close()
    }
}
