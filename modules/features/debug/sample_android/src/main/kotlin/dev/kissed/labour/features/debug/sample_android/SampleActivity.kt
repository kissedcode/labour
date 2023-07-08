package dev.kissed.labour.features.debug.sample_android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.kissed.android.util.context.showToast
import dev.kissed.kotlin.util.redux.Dispatcher
import dev.kissed.labour.features.debug.core.api.DebugFeature
import dev.kissed.labour.features.debug.ui_android.DebugFeatureView
import kotlinx.coroutines.flow.MutableStateFlow

class SampleActivity : AppCompatActivity() {

    private val state = MutableStateFlow(
        DebugFeature.State(
            isEnabled = true,
        ),
    )

    private val dispatcher = Dispatcher<DebugFeature.Event> { event -> showToast(event.javaClass.simpleName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val stateValue by state.collectAsState()
                DebugFeatureView(
                    state = DebugFeatureView.viewStateMapper(stateValue),
                    dispatcher = Dispatcher { dispatcher(it) },
                    restContent = {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color.White),
                        ) {
                            Text(
                                "rest content",
                                Modifier
                                    .align(Alignment.Center),
                            )
                        }
                    },
                )
            }
        }
    }
}
