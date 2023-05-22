package dev.kissed.labour

import dev.kissed.kotlin.util.redux.Action

sealed interface AppAction : Action {
    object TimerStartStop : AppAction
}
