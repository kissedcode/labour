package dev.kissed.labour

import dev.kissed.labour.common.models.Contraction
import dev.kissed.labour.features.timer.TimerState
import dev.kissed.labour.features.timer.isCounting

class AppReducer {

    fun AppState.reduce(action: AppAction): AppState {
        return when (action) {
            AppAction.TimerStartStop -> {
                val wasCounting = timerState.isCounting

                // fixme kissed: not clean!!!
                val newCounting = if (wasCounting) null else TimerState.Counting(startMs = System.currentTimeMillis())
                val newContraction = timerState.counting?.let {
                    Contraction(
                        startMs = it.startMs,
                        stopMs = System.currentTimeMillis(),
                    )
                }
                val newContractions = timerState.contractions.toMutableList().also { contractions ->
                    newContraction?.let(contractions::add)
                }

                copy(
                    timerState = timerState.copy(
                        counting = newCounting,
                        contractions = newContractions,
                    ),
                )
            }
        }
    }
}
