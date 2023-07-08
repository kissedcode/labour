package dev.kissed.kotlin.util.redux

fun interface Dispatcher<E : ReduxEvent> {

    operator fun invoke(event: E)

    companion object {
        val DUMMY = Dispatcher<ReduxEvent> { }
    }
}
