import kotlinx.coroutines.*
import java.io.File
import java.util.*
import kotlin.system.*
import java.util.Queue
import java.util.LinkedList
suspend fun CoroutineScope.massiveRun(x:Queue<String>,y:Queue<String>) {
            val jobs = List(2)
            {
                launch { repeat(2) {
                    File(x.poll()).writeText(y.poll())
                } }
            }
            jobs.forEach { it.join() }
        }


val mtContext = newFixedThreadPoolContext(5, "mtPool")
val fisiere: Queue<String> = LinkedList<String>(listOf("fisier1.txt","fisier2.txt","fisier3.txt","fisier4.txt"))
var de_printat= LinkedList<String>(listOf("fisier1.txt","fisier2.txt","fisier3.txt","fisier4.txt"))
fun main() = runBlocking<Unit> {
    CoroutineScope(mtContext).massiveRun (fisiere, de_printat)

}
