package dev.kissed.labour.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.kissed.labour.core.AppState
import dev.kissed.labour.view.timer.TimerView
import dev.kissed.labour.view.timer.TimerViewState

data class AppViewState(
    val timerState: TimerViewState,
) {
    companion object {

        fun fromAppState(appState: AppState): AppViewState = AppViewState(
            timerState = TimerViewState.viewStateMapper(appState.timerState),
        )
    }
}

@Composable
fun AppView(state: AppViewState) {
    MaterialTheme {
        TimerView(state = state.timerState)
    }
}
