package pages

import base.BasePage
import com.microsoft.playwright.Page
import config.Config

class LoginPage(page: Page) : BasePage(page) {
    private val username = "[data-test=\"username\"]"
    private val password = "[data-test=\"password\"]"
    private val loginButton = "[data-test=\"login-button\"]"
    private val errorMessage = "[data-test=\"error\"]"
    private val productsTitle = "[data-test=\"title\"]"

    fun open() = apply {
        page.navigate(Config.baseUrl())
        visible(username) // ensure form is ready before typing
    }

    fun login(user: String, pass: String) = apply {
        clearAndType(username, user)
        clearAndType(password, pass)
        clickWithRetry(loginButton)
    }

    fun waitForProducts(): Boolean = waitFor(productsTitle)
    fun waitForError(): Boolean = waitFor(errorMessage)
}
