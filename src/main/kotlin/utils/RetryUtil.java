package utils;

import java.time.Duration;

public final class RetryUtil {
    private RetryUtil() {}

    public static void run(int attempts, Duration delay, Runnable action) {
        RuntimeException last = null;
        for (int i = 1; i <= attempts; i++) {
            try {
                action.run();
                return;
            } catch (RuntimeException e) {
                last = e;
                if (i < attempts) {
                    try { Thread.sleep(delay.toMillis()); } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw e;
                    }
                }
            }
        }
        throw last;
    }
}
