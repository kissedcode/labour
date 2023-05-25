package dev.kissed.labour.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.kissed.labour.core.AppState
import dev.kissed.labour.view.timer.TimerView
import dev.kissed.labour.view.timer.TimerView.State

object AppView {

    data class State(
        val timerState: TimerView.State,
    ) {
        companion object {
            fun fromAppState(appState: AppState): State = State(
                timerState = TimerView.State.viewStateMapper(appState.timerState),
            )
        }
    }

    @Composable
    operator fun invoke(state: State) {
        MaterialTheme {
            TimerView(state = state.timerState)
        }
    }
}
