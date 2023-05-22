package dev.kissed.kotlin.util.logging

expect fun dlog(msg: String)

expect fun elog(msg: String, error: Throwable? = null)
