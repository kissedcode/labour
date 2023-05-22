package dev.kissed.labour

import kotlin.time.Duration.Companion.seconds

class AppReducer {

    fun AppState.reduce(action: AppAction): AppState {
        return when (action) {
            AppAction.TimerStartStop -> {
                val wasCounting = timerState.isCounting
                copy(
                    timerState = timerState.copy(
                        isCounting = !timerState.isCounting,
                        time = if (wasCounting) 10.seconds else 0.seconds,
                    ),
                )
            }
        }
    }
}
