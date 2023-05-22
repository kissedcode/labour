package dev.kissed.kotlin.util.logging

import android.util.Log

private const val TAG = "+++++++++"

actual fun dlog(msg: String) {
    Log.d(TAG, msg)
}

actual fun elog(msg: String, error: Throwable?) {
    Log.e(TAG, msg, error)
}
