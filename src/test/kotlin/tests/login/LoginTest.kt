package tests.login

import base.BaseTest
import com.microsoft.playwright.Page
import driver.PlaywrightManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.LoginPage

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
            .waitForProductsPage().assertOnProductsPage()
    }
}
