package dev.kissed.labour.core

import dev.kissed.labour.features.debug.core.api.DebugFeature

class AppReducer {

    fun AppState.reduce(action: AppAction): AppState = when (action) {
        is AppAction.FeatureDebugAction -> copy(
            debug = DebugFeature.reducer(action.action, debug)
        )
    }
//        return when (event) {
//            AppEvent.TimerStartStop -> {
//                val wasCounting = timer.isCounting
//
//                // fixme kissed: not clean!!!
//                val newCounting = if (wasCounting) null else TimerState.Counting(startMs = System.currentTimeMillis())
//                val newContraction = timer.counting?.let {
//                    Contraction(
//                        startMs = it.startMs,
//                        stopMs = System.currentTimeMillis(),
//                    )
//                }
//                val newContractions = timer.contractions.toMutableList().also { contractions ->
//                    newContraction?.let(contractions::add)
//                }
//
//                copy(
//                    timer = timer.copy(
//                        counting = newCounting,
//                        contractions = newContractions,
//                    ),
//                )
//            }
//        }
}
