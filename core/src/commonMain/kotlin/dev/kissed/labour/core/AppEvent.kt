package dev.kissed.labour.core

import dev.kissed.kotlin.util.redux.ReduxAction
import dev.kissed.kotlin.util.redux.ReduxEvent
import dev.kissed.labour.features.debug.core.api.DebugFeature

sealed interface AppEvent : ReduxEvent {
//    object TimerStartStop : AppEvent

    // features
    data class FeatureDebugEvent(val event: DebugFeature.Event) : AppEvent
}

sealed interface AppAction : ReduxAction {
    data class FeatureDebugAction(val action: DebugFeature.Action) : AppAction
}