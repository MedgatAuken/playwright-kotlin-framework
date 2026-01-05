package driver

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright

object PlaywrightManager {
    private val playwright = ThreadLocal<Playwright>()
    private val browser = ThreadLocal<Browser>()
    private val context = ThreadLocal<BrowserContext>()
    private val page = ThreadLocal<Page>()

    fun init() {
        val pw = Playwright.create()
        playwright.set(pw)

        val br = BrowserFactory.create(pw)
        browser.set(br)

        val ctx = PageProvider.newContext(br)
        context.set(ctx)

        page.set(PageProvider.newPage(ctx))
    }

    fun page(): Page? = page.get()
    fun context(): BrowserContext? = context.get()

    fun close() {
        try { context.get()?.close() } catch (_: Exception) {}
        try { browser.get()?.close() } catch (_: Exception) {}
        try { playwright.get()?.close() } catch (_: Exception) {}

        context.remove()
        browser.remove()
        playwright.remove()
        page.remove()
    }

    fun getPage(): Page {
        return page.get() ?: throw IllegalStateException("Playwright Page is not initialized. Did you forget to call PlaywrightManager.init()?")
    }
}
