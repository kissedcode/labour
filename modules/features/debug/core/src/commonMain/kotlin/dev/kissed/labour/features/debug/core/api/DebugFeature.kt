package dev.kissed.labour.features.debug.core.api

import dev.kissed.kotlin.util.redux.ReduxAction
import dev.kissed.kotlin.util.redux.ReduxEvent
import kotlinx.serialization.Serializable

object DebugFeature {

    // region states

    @Serializable
    data class State(
        val isEnabled: Boolean,
    )

    data class ExternalState(
        val agon: String = "agon",
    )

    // endregion

    // region events and actions

    sealed interface Event : ReduxEvent

    sealed interface ViewEvent : Event {
        object ElementClickClearContractions : ViewEvent
    }

    sealed interface Action : ReduxAction {
        object Increment : Action
    }

    sealed interface OutEvent : ReduxEvent {
        object RequestClearContractions : OutEvent
    }

    sealed interface InputEvent : ReduxEvent {
        object RequestDoSmth : InputEvent
    }

    sealed interface ManagedEvent {
        data class Action(val action: DebugFeature.Action) : ManagedEvent
        data class OutEvent(val outEvent: DebugFeature.OutEvent) : ManagedEvent
    }

    // endregion

    fun eventManager(event: Event, state: State, externalState: ExternalState): List<ManagedEvent> = when (event) {
        is ViewEvent -> when (event) {
            ViewEvent.ElementClickClearContractions -> listOf(
                ManagedEvent.Action(Action.Increment),
                ManagedEvent.OutEvent(OutEvent.RequestClearContractions),
            )
        }
    }

    fun reducer(action: Action, state: DebugFeature.State): DebugFeature.State = state
}
