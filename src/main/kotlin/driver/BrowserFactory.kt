package driver

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import config.Config

object BrowserFactory {
    fun create(playwright: Playwright): Browser {
        val commonOpts = BrowserType.LaunchOptions()
            .setHeadless(Config.headless())
            .setSlowMo(Config.slowMoMs().toDouble())

        return when (Config.browser().lowercase()) {
            "firefox" -> playwright.firefox().launch(commonOpts)
            "webkit" -> playwright.webkit().launch(commonOpts)
            "chromium" -> playwright.chromium().launch(commonOpts)
            "chrome" -> playwright.chromium().launch(
                BrowserType.LaunchOptions()
                    .setHeadless(Config.headless())
                    .setSlowMo(Config.slowMoMs().toDouble())
                    .setChannel("chrome")
            )
            "msedge", "edge" -> playwright.chromium().launch(
                BrowserType.LaunchOptions()
                    .setHeadless(Config.headless())
                    .setSlowMo(Config.slowMoMs().toDouble())
                    .setChannel("msedge")
            )
            else -> throw IllegalArgumentException("Unsupported browser: ${Config.browser()} (use chromium|firefox|webkit|chrome|msedge)")
        }
    }
}

