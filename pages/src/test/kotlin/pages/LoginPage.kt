package pages

import com.microsoft.playwright.Page

class LoginPage(private val page: Page) {

    fun open() = apply {
        page.navigate("https://example.com/login")
    }

    fun login(user: String, pass: String) = apply {
        page.fill("#username", user)
        page.fill("#password", pass)
        page.click("#login")
    }
}
