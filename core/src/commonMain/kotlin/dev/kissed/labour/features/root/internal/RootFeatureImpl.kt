package dev.kissed.labour.features.root.internal

import dev.kissed.labour.features.root.api.RootFeature
import dev.kissed.labour.features.root.api.RootFeature.Event
import dev.kissed.labour.features.root.api.RootFeature.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class RootFeatureImpl : RootFeature {

    private val _state = MutableStateFlow(State())

    override val state: StateFlow<State>
        get() = _state

    override fun dispatch(event: Event) {
        val newState = state.value.reduce(event)
        _state.value = newState
    }

    private fun State.reduce(event: Event): State {
        return when (event) {
            Event.BackClicked -> copy(
                child = if (child == RootFeature.Child.Settings) RootFeature.Child.Timer else child,
            )

            Event.NextClicked -> copy(
                child = if (child == RootFeature.Child.Timer) RootFeature.Child.Settings else child,
            )
        }
    }
}
