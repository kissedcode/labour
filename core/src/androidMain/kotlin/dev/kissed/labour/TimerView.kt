package dev.kissed.labour

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.kissed.labour.features.timer.TimerState

data class TimerViewState(
    val isCountButtonStartStop: Boolean,
    val duration: String,
) {
    companion object {
        fun viewStateMapper(state: TimerState): TimerViewState = TimerViewState(
            isCountButtonStartStop = !state.isCounting,
            duration = state.time.toString(),
        )
    }
}

@Composable
fun TimerView(state: TimerViewState) {
    Box(
        Modifier.fillMaxSize(),
    ) {
        Text(
            state.duration,
            Modifier.align(Alignment.Center),
        )
    }
}
