package config

import java.util.*

object Config {
    private val props = Properties()

    init {
        val stream = Config::class.java.classLoader.getResourceAsStream("application.properties")
            ?: throw RuntimeException("application.properties not found in resources")
        stream.use {
            props.load(it)
        }
    }

    private fun getRequired(key: String): String {
        val v = props.getProperty(key)?.trim()
        require(!v.isNullOrBlank()) { "Missing required property: $key" }
        return v
    }

    private fun get(key: String, def: String): String {
        val v = props.getProperty(key)?.trim()
        return if (v.isNullOrBlank()) def else v
    }

    fun baseUrl(): String = getRequired("baseUrl")
    fun browser(): String = get("browser", "chromium")
    fun headless(): Boolean = get("headless", "true").toBoolean()
    fun timeoutMs(): Int = get("timeoutMs", "30000").toInt()
    fun slowMoMs(): Int = get("slowMoMs", "0").toInt()
    fun retries(): Int = get("retries", "1").toInt()
    fun trace(): Boolean = get("trace", "false").toBoolean()
    fun video(): Boolean = get("video", "false").toBoolean()
}

