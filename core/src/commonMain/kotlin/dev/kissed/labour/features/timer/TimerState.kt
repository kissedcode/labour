package dev.kissed.labour.features.timer

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class TimerState(
    val isCounting: Boolean,
    val time: Duration,
) {
    companion object {
        val INITIAL = TimerState(
            isCounting = false,
            time = 0.seconds,
        )
    }
}
