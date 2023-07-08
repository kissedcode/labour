package dev.kissed.labour.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.kissed.kotlin.util.logging.elog
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.kotlin.util.redux.ReduxEvent
import dev.kissed.labour.core.AppEvent

object AppDispatcher { // fixme kissed: remove?

    private val local: ProvidableCompositionLocal<Dispatcher<AppEvent>> = staticCompositionLocalOf {
        Dispatcher {
            elog("LocalDispatcher has not been set!")
        }
    }

    val dispatch: Dispatcher<AppEvent>
        @Composable
        get() = local.current

    @Composable
    operator fun invoke(dispatcher: Dispatcher<AppEvent>, content: @Composable () -> Unit) {
        CompositionLocalProvider(local provides dispatcher) {
            content()
        }
    }
}
