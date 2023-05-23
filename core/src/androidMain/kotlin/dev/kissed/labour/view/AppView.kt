package dev.kissed.labour.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kissed.labour.AppAction.TimerStartStop
import dev.kissed.labour.AppState
import dev.kissed.labour.LocalDispatcher

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
                ContractionsView(state = state.timerState)
            }

            Box(
                Modifier
                    .requiredHeight(100.dp)
                    .fillMaxWidth()
                    .background(if (state.timerState.isCountButtonStartStop) Color.Green else Color.Red)
                    .clickable { dispatch(TimerStartStop) },
            ) {
                Text(
                    if (state.timerState.isCountButtonStartStop) "START" else "STOP",
                    Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}
