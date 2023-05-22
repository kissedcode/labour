package dev.kissed.labour

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kissed.labour.AppAction.TimerStartStop

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
    val dispatch = LocalDispatcher.current

    MaterialTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                TimerView(state = state.timerState)
            }

            val context = LocalContext.current
            Text(
                if (state.timerState.isCountButtonStartStop) "START" else "STOP",
                Modifier
                    .requiredHeight(100.dp)
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .clickable { dispatch(TimerStartStop) },
                color = if (state.timerState.isCountButtonStartStop) Color.Green else Color.Red,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
            )
        }
    }
}
