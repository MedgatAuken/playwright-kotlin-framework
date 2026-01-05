package base

import com.microsoft.playwright.Page
import com.microsoft.playwright.TimeoutError
import utils.RetryUtil
import java.time.Duration

abstract class BasePage(protected val page: Page) {
    private companion object {
        const val RETRIES = 3
        val DELAY: Duration = Duration.ofMillis(300)
    }

    fun visible(selector: String) {
        page.waitForSelector(selector, Page.WaitForSelectorOptions()
            .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE))
    }

    fun hidden(selector: String) {
        page.waitForSelector(selector, Page.WaitForSelectorOptions()
            .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN))
    }

    fun getTitle(): String = page.title()

    fun fillWithRetry(selector: String, value: String) {
        RetryUtil.run(RETRIES, DELAY) { page.fill(selector, value) }
    }

    fun clickWithRetry(selector: String) {
        RetryUtil.run(RETRIES, DELAY) { page.click(selector) }
    }

    fun waitFor(selector: String): Boolean = try {
        page.waitForSelector(selector)
        true
    } catch (_: TimeoutError) {
        false
    }

    fun clearAndType(selector: String, value: String) {
        RetryUtil.run(RETRIES, DELAY) {
            page.fill(selector, "")
            page.fill(selector, value)
        }
    }

    fun press(selector: String, key: String) {
        RetryUtil.run(RETRIES, DELAY) { page.press(selector, key) }
    }

    fun selectOption(selector: String, value: String) {
        RetryUtil.run(RETRIES, DELAY) { page.selectOption(selector, value) }
    }

    fun check(selector: String) {
        RetryUtil.run(RETRIES, DELAY) { page.check(selector) }
    }
}
