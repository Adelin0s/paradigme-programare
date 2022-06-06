import kotlinx.coroutines.*
import java.util.*


    fun main(args: Array<String>) {

        val nums: Queue<Int> = LinkedList<Int>(listOf(1, 2, 3,6))
        val t1=Thread1(nums.poll())
        val t2=Thread1(nums.poll())
        val t3=Thread1(nums.poll())
        val t4=Thread1(nums.poll())
        t1.run()
        t2.run()
        t3.run()
        t4.run()
    }


