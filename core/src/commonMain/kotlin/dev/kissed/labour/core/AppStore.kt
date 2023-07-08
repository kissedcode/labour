package dev.kissed.labour.core

import dev.kissed.kotlin.util.logging.dlog
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.kotlin.util.redux.ReduxCommand
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

class AppStore(
    initialState: AppState,
    private val eventManager: (AppEvent, AppState) -> List<ReduxCommand>,
    private val reducer: AppState.(AppAction) -> AppState = { this },
    private val scope: CoroutineScope,
) : Dispatcher<AppEvent> {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AppState>
        get() = _state

    private val _events = MutableSharedFlow<AppEvent>(extraBufferCapacity = Int.MAX_VALUE)

    private val _actions: Flow<AppAction> = _events.flatMapConcat {
        eventManager(it, state.value).asFlow().filterIsInstance()
    }

    init {
        scope.launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.Default) {
            _actions.collect { action ->
                val newState = state.value.reducer(action)
                _state.tryEmit(newState)
            }
        }
    }

    // Dispatcher
    override fun invoke(event: AppEvent) {
        dlog("event: $event")
        _events.tryEmit(event)
    }
}
