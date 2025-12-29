package driver;

import com.microsoft.playwright.*;

public final class PlaywrightManager {
    private PlaywrightManager() {}

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public static void init() {
        Playwright pw = Playwright.create();
        playwright.set(pw);

        Browser br = BrowserFactory.create(pw);
        browser.set(br);

        BrowserContext ctx = PageProvider.newContext(br);
        context.set(ctx);

        page.set(PageProvider.newPage(ctx));
    }

    public static Page page() { return page.get(); }
    public static BrowserContext context() { return context.get(); }

    public static void close() {
        try { if (context.get() != null) context.get().close(); } catch (Exception ignored) {}
        try { if (browser.get() != null) browser.get().close(); } catch (Exception ignored) {}
        try { if (playwright.get() != null) playwright.get().close(); } catch (Exception ignored) {}

        context.remove();
        browser.remove();
        playwright.remove();
        page.remove();
    }
}
