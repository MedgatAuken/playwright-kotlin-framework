package pages

import assertions.UiAssert.assertText
import assertions.UiAssert.assertVisible
import base.BasePage
import com.microsoft.playwright.Page
import config.Config

class LoginPage(page: Page) : BasePage(page) {
    private val username = "data-test=username"
    private val password = "data-test=password"
    private val loginButton = "data-test=login-button"
    private val productsTitle = "data-test=title"
    private val inventoryUrl = "**/inventory.html"
    private val inventoryItem = ".inventory_item"

    fun open() = apply {
        page.navigate(Config.baseUrl())
        visible(username)
    }

    fun login(user: String, pass: String) = apply {
        clearAndType(username, user)
        clearAndType(password, pass)
        clickWithRetry(loginButton)
    }

    fun waitForProductsPage() = apply {
        waitForUrl(inventoryUrl)
        visible(productsTitle)
    }

    fun assertOnProductsPage() = apply {
        assertVisible(page, productsTitle)
        assertText(page,productsTitle, "Products")
    }

    fun getInventoryItemCount(): Int {
        return count(inventoryItem)
    }
}
