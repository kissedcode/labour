package dev.kissed.labour.core

import dev.kissed.kotlin.util.redux.Action

sealed interface AppAction : Action {
    object TimerStartStop : AppAction
}
