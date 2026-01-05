package tests.smoke

import assertions.UiAssert
import base.BaseTest
import com.microsoft.playwright.Page
import driver.PlaywrightManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.LoginPage
import utils.Waiter

class SmokeTest : BaseTest() {
    private lateinit var page: Page
    private lateinit var loginPage: LoginPage
    private val username = "standard_user"
    private val password = "secret_sauce"

    @BeforeEach
    fun setup() {
        page = PlaywrightManager.getPage()
        loginPage = LoginPage(page).open()
    }

    @Test
    fun inventory_page_loads() {
        loginPage.login(username, password)

        Waiter.waitUrl(page, "**/inventory.html")
        Waiter.waitVisible(page, "[data-test=\"title\"]")

        UiAssert.urlContains(page, "inventory.html")
        UiAssert.assertText(page, "[data-test=\"title\"]", "Products")

        val itemCount = page.locator("[data-test=\"inventory-item\"]").count()
        assertThat(itemCount).isGreaterThan(0)
    }
}
