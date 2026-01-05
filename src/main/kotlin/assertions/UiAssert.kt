package assertions

import com.microsoft.playwright.Page
import org.assertj.core.api.Assertions

object UiAssert {
    fun urlContains(page: Page, part: String) {
        Assertions.assertThat(page.url()).contains(part)
    }

    fun titleIs(page: Page, expected: String) {
        Assertions.assertThat(page.title()).isEqualTo(expected)
    }

    fun assertVisible(page: Page, locator: String) {
        Assertions.assertThat(page.isVisible(locator))
            .withFailMessage("Expected element '$locator' to be visible")
            .isTrue()
    }

    fun assertText(page: Page, locator: String, expected: String) {
        val actual = page.textContent(locator) ?: ""
        Assertions.assertThat(actual)
            .withFailMessage("Text for '$locator' did not match")
            .isEqualTo(expected)
    }

    fun assertContainsText(page: Page, locator: String, part: String) {
        val actual = page.textContent(locator) ?: ""
        Assertions.assertThat(actual)
            .withFailMessage("Text for '$locator' did not contain expected part")
            .contains(part)
    }
}