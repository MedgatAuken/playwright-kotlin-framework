package config;

import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) {
                throw new RuntimeException("application.properties not found in resources");
            }
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    private Config() {}

    private static String getRequired(String key) {
        String v = props.getProperty(key);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return v.trim();
    }

    private static String get(String key, String def) {
        String v = props.getProperty(key);
        return (v == null || v.isBlank()) ? def : v.trim();
    }

    public static String baseUrl() { return getRequired("baseUrl"); }
    public static String browser() { return get("browser", "chromium"); }
    public static boolean headless() { return Boolean.parseBoolean(get("headless", "true")); }
    public static int timeoutMs() { return Integer.parseInt(get("timeoutMs", "30000")); }
    public static int slowMoMs() { return Integer.parseInt(get("slowMoMs", "0")); }
    public static int retries() { return Integer.parseInt(get("retries", "1")); }
    public static boolean trace() { return Boolean.parseBoolean(get("trace", "false")); }
    public static boolean video() { return Boolean.parseBoolean(get("video", "false")); }
}
