package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

fun sum(n:Int) :Int {
    var temp:Int=0;
    for(index in 0..n)
        temp+=index;
    return temp;
}

suspend fun runAsync(queue: Queue<Int>) = coroutineScope {
    val q1 = queue.peek()
    queue.remove()
    val q2=queue.peek()
    queue.remove()
    val q3 = queue.peek()
    queue.remove()
    val q4 = queue.peek()

    launch {
        println(sum(q1))
    }

    launch {
        println(sum(q2))
    }

    launch {
        println(sum(q3))
    }

    launch {
        println(sum(q4))
    }
}

fun main(args : Array<String>): Unit = runBlocking {

    var queue: Queue<Int> = LinkedList<Int>(listOf(5, 3, 4, 7))

    runAsync(queue)
}