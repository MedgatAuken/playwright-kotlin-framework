package base

import assertions.UiAssert
import com.microsoft.playwright.Page
import com.microsoft.playwright.TimeoutError
import config.Config
import utils.RetryUtil
import java.time.Duration

abstract class BasePage(protected val page: Page) {
    private companion object {
        val RETRIES = Config.retries()
        val DELAY : Duration = Duration.ofMillis(Config.timeoutMs().toLong())
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

    fun waitForUrl(pattern: String) {
        page.waitForURL(pattern)
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

    fun assertUrlContains(part: String) = UiAssert.urlContains(page, part)
    fun assertVisible(locator : String) = UiAssert.assertVisible(page, locator)
    fun assertText(locator: String, expected: String) = UiAssert.assertText(page, locator, expected)

    fun count(selector: String): Int {
        return page.locator(selector).count()
    }
}
