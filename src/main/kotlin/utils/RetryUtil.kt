package utils

import java.time.Duration

object RetryUtil {
    fun run(attempts: Int, delay: Duration, action: () -> Unit) {
        var last: RuntimeException? = null
        for (i in 1..attempts) {
            try {
                action()
                return
            } catch (e: RuntimeException) {
                last = e
                if (i < attempts) {
                    try {
                        Thread.sleep(delay.toMillis())
                    } catch (ie: InterruptedException) {
                        Thread.currentThread().interrupt()
                        throw e
                    }
                }
            }
        }
        throw last ?: RuntimeException("Unknown error in retry")
    }
}

