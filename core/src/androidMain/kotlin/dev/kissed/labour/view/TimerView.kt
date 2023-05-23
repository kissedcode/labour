package dev.kissed.labour.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kissed.labour.features.timer.TimerState
import dev.kissed.labour.features.timer.isCounting
import kotlin.time.Duration.Companion.milliseconds

data class TimerViewState(
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
                isCountButtonStartStop = !state.isCounting,
                items = items,
            )
        }
    }
}

@Composable
fun BoxScope.ContractionsView(state: TimerViewState) {
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
