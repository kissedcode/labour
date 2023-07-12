package dev.kissed.labour.features.root.api

import dev.kissed.labour.features.root.internal.RootFeatureImpl
import kotlinx.coroutines.flow.StateFlow

interface RootFeature {

    // region State

    data class State(val child: Child = Child.Timer)

    sealed interface Child {
        object Timer : Child
        object Settings : Child
    }

    val state: StateFlow<State>

    // endregion

    // region Events

    sealed interface Event {
        object BackClicked : Event
        object NextClicked : Event
    }

    fun dispatch(event: Event)

    // endregion

    companion object {

        fun create(): RootFeature {
            return RootFeatureImpl()
        }
    }
}
