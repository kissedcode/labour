package dev.kissed.labour.view.timer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kissed.labour.AppAction
import dev.kissed.labour.features.timer.TimerState
import dev.kissed.labour.features.timer.isCounting
import dev.kissed.labour.view.LocalDispatcher
import kotlin.time.Duration.Companion.milliseconds

data class TimerViewState(
    val isCountingWarning: Boolean,
    val isCountButtonStartStop: Boolean,
    val items: List<TimerItemViewState>,
) {
    sealed class TimerItemViewState() {

        abstract val durationText: String

        data class ContractionViewState(
            val number: Int,
            override val durationText: String,
        ) : TimerItemViewState()

        data class PauseViewState(
            override val durationText: String,
        ) : TimerItemViewState()
    }

    companion object {
        fun viewStateMapper(state: TimerState): TimerViewState {
            val items = mutableListOf<TimerItemViewState>()
            state.contractions.firstOrNull()?.let {
                items.add(
                    TimerItemViewState.ContractionViewState(
                        number = 0,
                        durationText = (it.stopMs - it.startMs)
                            .milliseconds.toString(),
                    ),
                )
            }
            state.contractions.windowed(2).forEachIndexed { idx, (prev, next) ->
                items.add(
                    TimerItemViewState.PauseViewState(
                        durationText = (next.startMs - prev.stopMs)
                            .milliseconds.toString(),
                    ),
                )
                items.add(
                    TimerItemViewState.ContractionViewState(
                        number = idx + 1,
                        durationText = (next.stopMs - next.startMs)
                            .milliseconds.toString(),
                    ),
                )
            }

            return TimerViewState(
                isCountingWarning = state.isCounting,
                isCountButtonStartStop = !state.isCounting,
                items = items,
            )
        }
    }
}

@Composable
fun TimerView(state: TimerViewState) {
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
            if (state.isCountingWarning) {
                WarningAnimationBox()
            }
            ContractionsList(state = state)
        }

        Box(
            Modifier
                .requiredHeight(100.dp)
                .fillMaxWidth(),
        ) {
            TimerButton(state = state)
        }
    }
}

@Composable
fun BoxScope.ContractionsList(state: TimerViewState) {
    if (state.items.isEmpty()) {
        Text(
            "No contractions yet",
            Modifier.align(Alignment.Center),
        )
    } else {
        LazyColumn(
            Modifier.fillMaxSize(),
            reverseLayout = true,
        ) {
            items(state.items) { item ->
                when (item) {
                    is TimerViewState.TimerItemViewState.ContractionViewState -> {
                        Text(
                            "[${item.number}] contraction, duration: ${item.durationText}",
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(8.dp)
                                .background(Color.Cyan),
                            fontSize = 20.sp,
                        )
                    }

                    is TimerViewState.TimerItemViewState.PauseViewState -> {
                        Text(
                            "pause: ${item.durationText}",
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BoxScope.WarningAnimationBox() {
    val infiniteTransition = rememberInfiniteTransition()
    val alphaValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = alphaValue
            }
            .background(Color.Red),
    )
}

@Composable
private fun BoxScope.TimerButton(state: TimerViewState) {
    val dispatch = LocalDispatcher.current

    Box(
        Modifier
            .fillMaxSize()
            .background(if (state.isCountButtonStartStop) Color.Green else Color.Red)
            .clickable { dispatch(AppAction.TimerStartStop) },
    ) {
        Text(
            if (state.isCountButtonStartStop) "START" else "STOP",
            Modifier
                .align(Alignment.Center),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}
