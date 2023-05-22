package dev.kissed.labour

import dev.kissed.labour.features.timer.TimerState
import dev.kissed.labour.features.timer.isCounting
import kotlin.time.Duration.Companion.milliseconds

class AppReducer {

    fun AppState.reduce(action: AppAction): AppState {
        return when (action) {
            AppAction.TimerStartStop -> {
                val wasCounting = timerState.isCounting
                
                // fixme kissed: not clean!!!
                val newCounting = if (wasCounting) null else TimerState.Counting(startMs = System.currentTimeMillis())
                val newTime = timerState.counting?.let {
                    val durationMs = System.currentTimeMillis() - it.startMs
                    durationMs.milliseconds
                } ?: 0.milliseconds
                copy(
                    timerState = timerState.copy(
                        counting = newCounting,
                        time = newTime,
                    ),
                )
            }
        }
    }
}
