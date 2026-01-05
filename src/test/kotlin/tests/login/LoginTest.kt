package tests.login

import assertions.UiAssert
import base.BaseTest
import com.microsoft.playwright.Page
import driver.PlaywrightManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.LoginPage
import utils.Waiter

class LoginTest : BaseTest() {
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
    fun successful_login() {
        loginPage.login(username, password)

        Waiter.waitUrl(page, "**/inventory.html")
        Waiter.waitVisible(page, "[data-test=\"title\"]")

        UiAssert.urlContains(page, "inventory.html")
        UiAssert.assertVisible(page, "[data-test=\"title\"]")
        UiAssert.assertText(page, "[data-test=\"title\"]", "Products")
    }
}
