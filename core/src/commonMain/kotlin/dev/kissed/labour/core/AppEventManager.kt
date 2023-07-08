package dev.kissed.labour.core

import dev.kissed.kotlin.util.redux.ReduxCommand
import dev.kissed.labour.features.debug.core.api.DebugFeature

object AppEventManager {
    fun manage(event: AppEvent, state: AppState): List<ReduxCommand> = when (event) {
        is AppEvent.FeatureDebugEvent -> {
            DebugFeature.eventManager(event.event, state.debug, DebugFeature.ExternalState())
                .mapNotNull {
                    when (it) {
                        is DebugFeature.ManagedEvent.Action -> AppAction.FeatureDebugAction(it.action)
                        is DebugFeature.ManagedEvent.OutEvent -> when (it.outEvent) {
                            DebugFeature.OutEvent.RequestClearContractions -> null // todo
                        }
                    }
                }
        }
    }
}
