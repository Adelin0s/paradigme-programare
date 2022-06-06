import kotlinx.coroutines.*
import java.util.*
import kotlin.system.measureTimeMillis


suspend fun CoroutineScope.massiveRun(queue: Queue<Int>) {
        val jobs = List(4)
        {
            launch {  val x=q.poll()
            val sum=(x*(x+1))/2
            println("Suma este:$sum")
                delay(1000)
            }
        }
        jobs.forEach { it.join() }
    }

val mtContext = newFixedThreadPoolContext(4, "mtPool")
var q: Queue<Int> = LinkedList<Int>(listOf(9, 2, 3,4))
fun main() = runBlocking<Unit> {
    CoroutineScope(mtContext).massiveRun (q)
}
