import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    val d = scanner.nextInt()

    for (x in 0..1000) {
        if (a * x * x * x + b * x * x + c * x + d == 0) println(x)
    }
}