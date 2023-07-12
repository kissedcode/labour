package dev.kissed.labour.view.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.kissed.labour.features.root.api.RootFeature

@Composable
fun SettingsFeatureView(rootFeature: RootFeature) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "settings",
            Modifier
                .clickable { rootFeature.dispatch(RootFeature.Event.BackClicked) }
                .padding(16.dp),
        )
    }
}
