package dev.kissed.labour.core

import dev.kissed.kotlin.util.redux.Event

sealed interface AppEvent : Event {
    object TimerStartStop : AppEvent
}
