package dev.kissed.labour.view

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import dev.kissed.kotlin.util.logging.elog
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.labour.core.AppAction

val LocalDispatcher: ProvidableCompositionLocal<Dispatcher<AppAction>> = compositionLocalOf {
    Dispatcher {
        elog("LocalDispatcher has not been set!")
    }
}
