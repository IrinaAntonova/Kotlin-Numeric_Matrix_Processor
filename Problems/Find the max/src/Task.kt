import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val count = scanner.nextInt()
    val numbers = IntArray(count) { scanner.nextInt() }

    var currentIndex = 0
    var currentMax = Int.MIN_VALUE
    for (index in numbers.indices) {
        val item = numbers[index]
        if (item > currentMax) {
            currentMax = item
            currentIndex = index
        }
    }
    print(currentIndex)
}