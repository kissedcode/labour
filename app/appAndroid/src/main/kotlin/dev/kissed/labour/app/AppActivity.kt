package dev.kissed.labour.app

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.kissed.labour.core.AppState
import dev.kissed.labour.core.AppStore
import dev.kissed.labour.view.AppDispatcher
import dev.kissed.labour.view.AppView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppActivity : AppCompatActivity() {

    private val store: AppStore
        get() = appStore(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by store.state.collectAsState()

            AppDispatcher(dispatcher = store) {
                AppView(AppView.State.fromAppState(state))
            }
        }
    }

    override fun onStop() {
        super.onStop()

        getSharedPreferences("state", Context.MODE_PRIVATE).edit()
            .putString("state", Json { }.encodeToString(store.state.value))
            .apply()
    }

    companion object {

        private lateinit var store: AppStore

        private fun appStore(context: Context): AppStore {
            if (!this::store.isInitialized) {
                val savedState: AppState? = context.getSharedPreferences("state", Context.MODE_PRIVATE)
                    .getString("state", null)
                    ?.let { Json { }.decodeFromString(it) }

                store = AppStore(savedState ?: AppState.INITIAL)
            }
            return store
        }
    }
}
