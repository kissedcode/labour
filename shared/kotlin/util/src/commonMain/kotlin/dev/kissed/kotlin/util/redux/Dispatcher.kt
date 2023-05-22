package dev.kissed.kotlin.util.redux

fun interface Dispatcher<in A : Action> {

    operator fun invoke(action: A)

    companion object {
        val DUMMY = Dispatcher<Action> { }
    }
}
