import java.util.function.Predicate

fun main(args : Array<String>) {
    var list = mutableListOf<Int>(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    val greatThan5 = list.filter { it > 5}
    val chunk = greatThan5.chunked(2)

    var multiplicated = chunk.map{ it -> it[0] + it[1] }

    val sum = multiplicated.reduce { acc, i -> acc + i }
    println(sum)
}