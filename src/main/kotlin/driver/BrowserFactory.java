package driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import config.Config;

public final class BrowserFactory {
    private BrowserFactory() {}

    public static Browser create(Playwright playwright) {
        BrowserType.LaunchOptions opts = new BrowserType.LaunchOptions()
                .setHeadless(Config.headless())
                .setSlowMo(Config.slowMoMs());

        String b = Config.browser().toLowerCase();

        return switch (b) {
            case "firefox" -> playwright.firefox().launch(opts);
            case "webkit" -> playwright.webkit().launch(opts);
            case "chromium" -> playwright.chromium().launch(opts);
            case "chrome" -> playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Config.headless())
                            .setSlowMo(Config.slowMoMs())
                            .setChannel("chrome")
            );
            case "msedge", "edge" -> playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Config.headless())
                            .setSlowMo(Config.slowMoMs())
                            .setChannel("msedge")
            );
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + b + " (use chromium|firefox|webkit|chrome|msedge)"
            );
        };
    }
}
