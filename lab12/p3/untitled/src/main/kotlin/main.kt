import java.awt.geom.Point2D
import java.util.Scanner

fun main(args: Array<String>) {
// eg points : 00, 10, 11, 01
    val reader = Scanner(System.`in`)
    println("Number of points:")
    val n = reader.nextInt()

    var listx = mutableListOf<Int>()
    var listy = mutableListOf<Int>()

    for (i in 0 until n) {
        println("Point " + i + ":" )
        listx.add(reader.nextInt())
        listy.add(reader.nextInt())
    }

    var perimeter = 0.0

    val points = listx.zip(listy)

    for (i in 0 until n) {
        if (i == n-1) {
            perimeter += Point2D.distance(
                points[n-1].first.toDouble(), points[n-1].second.toDouble(),
                points[0].first.toDouble(), points[0].second.toDouble()
            )
            break
        }
        perimeter += Point2D.distance(
            points[i].first.toDouble(), points[i].second.toDouble(),
            points[i+1].first.toDouble(), points[i+1].second.toDouble()
        )
    }
    println(perimeter)
}