package dev.kissed.labour

import dev.kissed.labour.features.timer.TimerState
import kotlinx.serialization.Serializable

@Serializable
data class AppState(
    val timerState: TimerState,
) {
    companion object {
        val INITIAL: AppState = AppState(
            timerState = TimerState.INITIAL,
        )
    }
}
