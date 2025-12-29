package tests.login

import base.BaseTest
import config.Config
import driver.PlaywrightManager
import org.junit.jupiter.api.Test

class LoginTest : BaseTest() {

    @Test
    fun successful_login() {
        val page = PlaywrightManager.page()

        page.navigate(Config.baseUrl())
        page.fill("#user-name", "standard_user")
        page.fill("#password", "secret_sauce")
        page.click("#login-button")

        page.waitForURL("**/inventory.html")
    }
}
