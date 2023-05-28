package dev.kissed.kotlin.util.time

import kotlinx.datetime.LocalDateTime

expect fun LocalDateTime.format(pattern: String): String