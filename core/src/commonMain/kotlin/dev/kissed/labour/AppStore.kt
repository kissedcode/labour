package dev.kissed.labour

import dev.kissed.kotlin.util.logging.dlog
import dev.kissed.kotlin.util.redux.Dispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppStore : Dispatcher<AppAction> {

    private val _state = MutableStateFlow(AppState.INITIAL)
    val state: StateFlow<AppState>
        get() = _state
    
    private val reducer: AppReducer = AppReducer()

    // Dispatcher
    override fun invoke(action: AppAction) {
        dlog("dispatch: $action")

        val newState = with(reducer) {
            state.value.reduce(action)
        }
        _state.value = newState
    }
}
