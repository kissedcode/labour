package dev.kissed.labour.core

import dev.kissed.labour.common.models.Contraction
import dev.kissed.labour.features.timer.TimerState
import dev.kissed.labour.features.timer.isCounting

class AppReducer {

    fun AppState.reduce(event: AppEvent): AppState {
        return when (event) {
            AppEvent.TimerStartStop -> {
                val wasCounting = timer.isCounting

                // fixme kissed: not clean!!!
                val newCounting = if (wasCounting) null else TimerState.Counting(startMs = System.currentTimeMillis())
                val newContraction = timer.counting?.let {
                    Contraction(
                        startMs = it.startMs,
                        stopMs = System.currentTimeMillis(),
                    )
                }
                val newContractions = timer.contractions.toMutableList().also { contractions ->
                    newContraction?.let(contractions::add)
                }

                copy(
                    timer = timer.copy(
                        counting = newCounting,
                        contractions = newContractions,
                    ),
                )
            }
        }
    }
}
