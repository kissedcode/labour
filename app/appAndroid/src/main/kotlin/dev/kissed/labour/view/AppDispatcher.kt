package dev.kissed.labour.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.kissed.kotlin.util.logging.elog
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.labour.core.AppAction

object AppDispatcher {

    private val local: ProvidableCompositionLocal<Dispatcher<AppAction>> = staticCompositionLocalOf {
        Dispatcher {
            elog("LocalDispatcher has not been set!")
        }
    }

    val dispatch: Dispatcher<AppAction>
        @Composable
        get() = local.current

    @Composable
    operator fun invoke(dispatcher: Dispatcher<AppAction>, content: @Composable () -> Unit) {
        CompositionLocalProvider(local provides dispatcher) {
            content()
        }
    }
}
