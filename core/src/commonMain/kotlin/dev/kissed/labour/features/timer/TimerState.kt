package dev.kissed.labour.features.timer

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class TimerState(
    val counting: Counting?,
    val time: Duration,
) {
    data class Counting(
        val startMs: Long,
    )

    companion object {
        val INITIAL = TimerState(
            counting = null,
            time = 0.seconds,
        )
    }
}

val TimerState.isCounting: Boolean
    get() = counting != null
