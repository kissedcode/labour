package dev.kissed.labour

import dev.kissed.kotlin.util.redux.Action
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppStore {

    val _state = MutableStateFlow(AppState.INITIAL)
    val state: StateFlow<AppState>
        get() = _state

    fun action(action: Action) {
    }
}
