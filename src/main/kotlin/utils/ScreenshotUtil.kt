package utils

import com.microsoft.playwright.Page
import java.nio.file.Path

object ScreenshotUtil {
    fun take(page: Page, name: String): Path {
        val p = Path.of("build", "screenshots", "$name.png")
        page.screenshot(Page.ScreenshotOptions()
            .setPath(p)
            .setFullPage(true))
        return p
    }
}

