package dev.kissed.labour.view.root

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.IntOffset
import dev.kissed.labour.features.root.api.RootFeature
import dev.kissed.labour.view.settings.SettingsFeatureView
import dev.kissed.labour.view.timer.TimerFeatureView

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootFeatureView(feature: RootFeature) {
    val state by feature.state.collectAsState()

    AnimatedContent(
        targetState = state.child,
        transitionSpec = { slideIn { IntOffset(it.width, 0) } with slideOut { IntOffset(-it.width, 0) } },
    ) {
        when (val child = state.child) {
            RootFeature.Child.Settings -> {
                SettingsFeatureView(rootFeature = feature)
            }

            RootFeature.Child.Timer -> {
                TimerFeatureView(rootFeature = feature)
            }
        }
    }
}
