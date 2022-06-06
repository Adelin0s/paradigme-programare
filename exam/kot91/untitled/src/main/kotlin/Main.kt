import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex() // shared mutex across all objects that inheritance from BaseClass

var hash = HashMap<Int, Int>()

interface Operation {
    abstract fun run(hash: HashMap<Int, Int>, value: Int) : HashMap<Int, Int>
}

class Adunare() : Operation {
    override fun run(hash: HashMap<Int, Int>, value: Int): HashMap<Int, Int>{
        return hash.entries.associate { it.key to it.value + value } as HashMap<Int, Int>
    }
}

class Scadere() : Operation {
    override fun run(hash: HashMap<Int, Int>, value: Int): HashMap<Int, Int> {
        return hash.entries.associate { it.key to it.value - value } as HashMap<Int, Int>
    }
}

abstract class Executor(private val hash: HashMap<Int, Int>, private val operation: Operation) {
//    fun Adunare(value: Int) : HashMap<Int, Int> {
//        return hash.entries.associate { it.key to it.value + value } as HashMap<Int, Int>
//    }
//
//    fun Scadere(value: Int) : HashMap<Int, Int> {
//        return hash.entries.associate { it.key to it.value - value } as HashMap<Int, Int>
//    }
//
//    fun Inmultire(value: Int) : HashMap<Int, Int> {
//        return hash.entries.associate { it.key to it.value * value } as HashMap<Int, Int>
//    }
//
//    fun Impartire(value: Int) : HashMap<Int, Int> {
//        return hash.entries.associate { it.key to it.value / value } as HashMap<Int, Int>
//    }

    fun run(op: HashMap<Int, Int>, value: Int) {
        operation.run(hash, value)
//        var h = HashMap<Int, Int>()
//
//        when (op) {
//            "adunare" -> h = Adunare(value)
//            "scadere" -> h = Scadere(value)
//            "inmultire" -> h = Inmultire(value)
//            "impartire" -> h = Impartire(value)
//            else -> println("Nothing to do")
//        }
//        return h
    }
}

class Object1(var hash: HashMap<Int, Int>, operation: Operation) : Executor(hash, operation) {
    suspend fun run() = coroutineScope {
        launch {
            mutex.withLock {
                super.run(hash, 1)
                println("obj1")
            }
        }
    }
}

class Object2(var hash: HashMap<Int, Int>, operation: Operation) : Executor(hash, operation) {
    suspend fun run() = coroutineScope {
        launch {
            mutex.withLock {
                Thread.sleep(2000)
                super.run(hash, 1)
                println("obj2")
            }
        }
    }
}

fun main(args: Array<String>) {
    hash[0] = 0
    hash[1] = 1
    hash[2] = 2

    val op1 = Adunare()
    val op2 = Scadere()

    val obj2 = Object2(hash, op1)
    val obj1 = Object1(hash, op2)

    runBlocking {
        joinAll(obj1.run(), obj2.run())
    }
}
