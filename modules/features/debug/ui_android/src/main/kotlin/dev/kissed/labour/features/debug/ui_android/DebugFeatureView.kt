package dev.kissed.labour.features.debug.ui_android

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.kissed.kotlin.util.redux.ComposeDispatcherWrapper
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.labour.features.debug.core.api.DebugFeature
import dev.kissed.labour.features.debug.core.api.DebugFeature.ViewEvent

object DebugFeatureView {

    data class ViewState(
        val isEnabled: Boolean,
    )

    fun viewStateMapper(state: DebugFeature.State, externalState: DebugFeature.ExternalState? = null): ViewState {
        return ViewState(
            isEnabled = state.isEnabled,
        )
    }

    internal val dispatcherWrapper = ComposeDispatcherWrapper<ViewEvent>()

    @Composable
    operator fun invoke(state: ViewState, dispatcher: Dispatcher<ViewEvent>, restContent: @Composable () -> Unit) {
        dispatcherWrapper(dispatcher) {
            if (state.isEnabled) {
                val direction = LocalLayoutDirection.current
                val oppositeDirection = if (direction == LayoutDirection.Ltr) LayoutDirection.Rtl else LayoutDirection.Ltr

                CompositionLocalProvider(LocalLayoutDirection provides oppositeDirection) {
                    ModalDrawer(
                        drawerContent = {
                            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                                DrawerContent(state = state)
                            }
                        },
                    ) {
                        CompositionLocalProvider(LocalLayoutDirection provides direction) {
                            restContent()
                        }
                    }
                }
            } else {
                restContent()
            }
        }
    }
}

@Composable
private fun ColumnScope.DrawerContent(state: DebugFeatureView.ViewState) {
    Text(
        "Debug panel",
        Modifier.align(Alignment.CenterHorizontally),
    )
    Divider()
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan),
    ) {
        Text(state.isEnabled.toString())
        ElementButton(text = "Clear contractions", clicks = 0, clickEvent = ViewEvent.ElementClickClearContractions)
    }
}

@Composable
private fun ColumnScope.ElementButton(text: String, clicks: Int, clickEvent: ViewEvent) {
    val dispatch = DebugFeatureView.dispatcherWrapper.dispatch
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { dispatch(clickEvent) }
            .padding(8.dp),
    ) {
        Text(
            text,
            Modifier.weight(1f),
        )
        Text(
            "($clicks)",
            color = Color.Red,
        )
    }
}
