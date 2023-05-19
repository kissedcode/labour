package dev.kissed.labour

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class AppState(
    val isCounting: Boolean,
    val timerState: TimerState,
) {
    companion object {
        val INITIAL: AppState = AppState(
            isCounting = false,
            timerState = TimerState.INITIAL,
        )
    }
}

data class TimerState(
    val time: Duration,
) {
    companion object {
        val INITIAL = TimerState(
            time = 10.seconds,
        )
    }
}
