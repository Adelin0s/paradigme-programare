import kotlinx.coroutines.delay

class Thread1(var x:Int): Thread() {
    override fun run() {
        println(calculeaza(x))
    }

    fun calculeaza(n:Int):Int{
        var sum=(n*(n+1))/2
        return sum
    }


}