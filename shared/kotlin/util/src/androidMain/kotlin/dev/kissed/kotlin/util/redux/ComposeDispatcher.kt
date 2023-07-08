package dev.kissed.kotlin.util.redux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.kissed.kotlin.util.logging.elog

object ComposeDispatcher { // fixme kissed: remove?

    private val local: ProvidableCompositionLocal<Dispatcher<ReduxEvent>> = staticCompositionLocalOf {
        Dispatcher {
            elog("LocalDispatcher has not been set!")
        }
    }

    val dispatch: Dispatcher<ReduxEvent>
        @Composable
        get() = local.current

    @Composable
    operator fun invoke(dispatcher: Dispatcher<ReduxEvent>, content: @Composable () -> Unit) {
        CompositionLocalProvider(local provides dispatcher) {
            content()
        }
    }
}

class ComposeDispatcherWrapper<E : ReduxEvent> {
    private val local: ProvidableCompositionLocal<Dispatcher<E>> = staticCompositionLocalOf {
        Dispatcher {
            error("LocalDispatcher has not been set!")
        }
    }

    val dispatch: Dispatcher<E>
        @Composable
        get() = local.current

    @Composable
    operator fun invoke(dispatcher: Dispatcher<E>, content: @Composable () -> Unit) {
        CompositionLocalProvider(local provides dispatcher) {
            content()
        }
    }
}
