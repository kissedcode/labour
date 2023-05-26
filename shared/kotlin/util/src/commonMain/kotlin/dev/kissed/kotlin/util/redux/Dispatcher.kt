package dev.kissed.kotlin.util.redux

fun interface Dispatcher<in E : Event> {

    operator fun invoke(event: E)

    companion object {
        val DUMMY = Dispatcher<Event> { }
    }
}
