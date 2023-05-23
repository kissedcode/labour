package dev.kissed.labour.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.kissed.labour.AppStore
import dev.kissed.labour.view.AppView
import dev.kissed.labour.view.AppViewState
import dev.kissed.labour.LocalDispatcher

private val appStore = AppStore()

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by appStore.state.collectAsState()

            CompositionLocalProvider(LocalDispatcher provides appStore) {
                AppView(AppViewState.fromAppState(state))
            }
        }
    }
}
