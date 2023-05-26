package dev.kissed.labour.core

import dev.kissed.kotlin.util.logging.dlog
import dev.kissed.kotlin.util.redux.Dispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppStore(initialState: AppState) : Dispatcher<AppEvent> {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AppState>
        get() = _state
    
    private val reducer: AppReducer = AppReducer()

    // Dispatcher
    override fun invoke(event: AppEvent) {
        dlog("dispatch: $event")

        val newState = with(reducer) {
            state.value.reduce(event)
        }
        _state.value = newState
    }
}
