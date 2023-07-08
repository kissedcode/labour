package dev.kissed.labour.core

import dev.kissed.labour.features.debug.core.api.DebugFeature
import dev.kissed.labour.features.timer.TimerState
import kotlinx.serialization.Serializable

@Serializable
data class AppState(
    val timer: TimerState,
    val debug: DebugFeature.State,
) {
    companion object {
        val INITIAL: AppState = AppState(
            timer = TimerState.INITIAL,
            debug = DebugFeature.State(
                isEnabled = true,
            ),
        )
    }
}
