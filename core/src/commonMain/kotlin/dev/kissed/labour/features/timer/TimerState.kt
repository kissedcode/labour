package dev.kissed.labour.features.timer

import dev.kissed.labour.common.models.Contraction

data class TimerState(
    val counting: Counting?,
    val contractions: List<Contraction>,
) {
    data class Counting(
        val startMs: Long,
    )

    companion object {
        val INITIAL = TimerState(
            counting = null,
            contractions = emptyList(),
        )
    }
}

val TimerState.isCounting: Boolean
    get() = counting != null
