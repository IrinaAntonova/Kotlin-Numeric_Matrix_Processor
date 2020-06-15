import java.util.Scanner
import kotlin.math.acos
import kotlin.math.sqrt

fun main() {
    val scanner = Scanner(System.`in`)
    val first = Vector(scanner.nextInt(), scanner.nextInt())
    val second = Vector(scanner.nextInt(), scanner.nextInt())
    val multi = (first.i * second.i + first.j * second.j)
    val squares = first.lengthSquare * second.lengthSquare
    val cosVectors = multi * sqrt(squares.toDouble()) / squares
    println(Math.toDegrees(acos(cosVectors)))
}

data class Vector(val i: Int, val j: Int) {
    val lengthSquare: Int
        get() {
            return (i * i + j * j)
        }
}