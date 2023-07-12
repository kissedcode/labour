package dev.kissed.labour.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.kissed.labour.features.root.api.RootFeature
import dev.kissed.labour.view.root.RootFeatureView

@Composable
fun AppView(rootFeature: RootFeature) {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize(),
        ) {
            RootFeatureView(feature = rootFeature)
        }
    }
}

// object AppView {
//
//    data class State(
//        val timerState: TimerView.State,
//    ) {
//        companion object {
//            fun fromAppState(appState: AppState): State = State(
//                timerState = TimerView.State.viewStateMapper(appState.timer),
//            )
//        }
//    }
//
//    @Composable
//    operator fun invoke(appState: AppState, state: State, dispatcher: Dispatcher<AppEvent>) {
//        @Composable
//        fun appContent() {
//            TimerView(state = state.timerState)
//        }
//
//        MaterialTheme {
//            DebugFeatureView(
//                state = DebugFeatureView.viewStateMapper(appState.debug),
//                dispatcher = Dispatcher { dispatcher(AppEvent.FeatureDebugEvent(it)) },
//                restContent = { appContent() },
//            )
//        }
//    }
// }
