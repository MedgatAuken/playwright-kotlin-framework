package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Path;

public final class ScreenshotUtil {
    private ScreenshotUtil() {}

    public static Path take(Page page, String name) {
        Path p = Path.of("build", "screenshots", name + ".png");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(p)
                .setFullPage(true));
        return p;
    }
}
