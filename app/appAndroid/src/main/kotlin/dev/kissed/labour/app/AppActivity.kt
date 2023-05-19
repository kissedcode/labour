package dev.kissed.labour.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.kissed.labour.AppState
import dev.kissed.labour.AppView
import dev.kissed.labour.AppViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stateFlow = MutableStateFlow(AppState.INITIAL)

        setContent {
            val state by stateFlow.collectAsState()
            AppView(AppViewState.fromAppState(state))
        }
    }
}
