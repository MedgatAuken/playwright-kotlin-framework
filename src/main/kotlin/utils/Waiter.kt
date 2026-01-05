package utils

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.WaitForSelectorState

object Waiter {
    fun waitVisible(page: Page, selector: String) {
        page.waitForSelector(selector, Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE))
    }

    fun waitHidden(page: Page, selector: String) {
        page.waitForSelector(selector, Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.HIDDEN))
    }

    fun waitUrl(page: Page, pattern: String) {
        page.waitForURL(pattern)
    }
}
