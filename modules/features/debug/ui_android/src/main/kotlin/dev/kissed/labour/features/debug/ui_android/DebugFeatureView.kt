package dev.kissed.labour.features.debug.ui_android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.kissed.labour.features.debug.core.api.DebugFeature

@Composable
fun DebugFeatureView(state: DebugFeature.State, restContent: @Composable () -> Unit) {
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
}

@Composable
private fun ColumnScope.DrawerContent(state: DebugFeature.State) {
    Text(
        "Debug panel",
        Modifier.align(Alignment.CenterHorizontally),
    )
    Divider()
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan),
    ) {
        Text(state.isEnabled.toString())
    }
}
