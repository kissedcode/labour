package dev.kissed.labour.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import dev.kissed.labour.features.root.api.RootFeature
import dev.kissed.labour.view.AppView

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootFeature = RootFeature.create()

        setContent {
            AppView(rootFeature)
        }
    }
}
