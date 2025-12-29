package assertions;

import com.microsoft.playwright.Page;
import org.assertj.core.api.Assertions;

public final class UiAssert {
    private UiAssert() {}

    public static void urlContains(Page page, String part) {
        Assertions.assertThat(page.url()).contains(part);
    }

    public static void titleIs(Page page, String expected) {
        Assertions.assertThat(page.title()).isEqualTo(expected);
    }
}
