package dev.kissed.labour.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.kissed.labour.core.AppState
import dev.kissed.labour.features.debug.ui_android.DebugFeatureView
import dev.kissed.labour.view.timer.TimerView

object AppView {

    data class State(
        val timerState: TimerView.State,
    ) {
        companion object {
            fun fromAppState(appState: AppState): State = State(
                timerState = TimerView.State.viewStateMapper(appState.timer),
            )
        }
    }

    @Composable
    operator fun invoke(appState: AppState, state: State) {
        @Composable
        fun appContent() {
            TimerView(state = state.timerState)
        }

        MaterialTheme {
            DebugFeatureView(state = appState.debug) {
                appContent()
            }
        }
    }
}
