package driver

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import config.Config

object PageProvider {
    fun newContext(browser: Browser): BrowserContext {
        val ctxOpts = Browser.NewContextOptions()
        val ctx = browser.newContext(ctxOpts)
        ctx.setDefaultTimeout(Config.timeoutMs().toDouble())
        ctx.setDefaultNavigationTimeout(Config.timeoutMs().toDouble())
        return ctx
    }

    fun newPage(context: BrowserContext): Page = context.newPage()
}

