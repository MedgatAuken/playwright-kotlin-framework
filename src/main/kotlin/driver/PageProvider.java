package driver;

import com.microsoft.playwright.*;
import config.Config;

public final class PageProvider {
    private PageProvider() {}

    public static BrowserContext newContext(Browser browser) {
        Browser.NewContextOptions ctxOpts = new Browser.NewContextOptions();
        BrowserContext ctx = browser.newContext(ctxOpts);
        ctx.setDefaultTimeout(Config.timeoutMs());
        ctx.setDefaultNavigationTimeout(Config.timeoutMs());
        return ctx;
    }

    public static Page newPage(BrowserContext context) {
        return context.newPage();
    }
}
