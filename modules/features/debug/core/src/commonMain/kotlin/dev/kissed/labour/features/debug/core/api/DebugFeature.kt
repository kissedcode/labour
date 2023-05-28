package dev.kissed.labour.features.debug.core.api

import kotlinx.serialization.Serializable

object DebugFeature {

    // region states

    @Serializable
    data class State private constructor(
        val isEnabled: Boolean,
    ) {
        companion object {
            fun initial(): State = State(
                isEnabled = true,
            )
        }
    }

    // endregion
}
