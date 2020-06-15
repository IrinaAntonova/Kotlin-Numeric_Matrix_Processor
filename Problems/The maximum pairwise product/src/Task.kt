import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val count = scanner.nextInt()

    if (count < 2) println(scanner.nextInt())
    else {
        val numbers = IntArray(count) { scanner.nextInt() }
        numbers.sortDescending()
        println(numbers[0] * numbers[1])
    }
}