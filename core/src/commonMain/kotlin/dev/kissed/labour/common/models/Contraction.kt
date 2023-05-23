package dev.kissed.labour.common.models

import kotlinx.serialization.Serializable

@Serializable
data class Contraction(
    val startMs: Long,
    val stopMs: Long,
)
