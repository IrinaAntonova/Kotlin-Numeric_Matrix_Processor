import java.util.Scanner
import kotlin.math.floor

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val number = scanner.nextDouble()
    println(floor((number - floor(number)) * 10))
}