import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val count = scanner.nextInt()
    if (count > 1) {
        val numbers = IntArray(count) { scanner.nextInt() }
        var toRemember = 0
        for (index in 0 until numbers.lastIndex) {
            toRemember = numbers[index + 1]
            numbers[index + 1] = numbers[0]
            numbers[0] = toRemember
        }
        numbers[0] = toRemember
        numbers.forEach { item -> print("$item ") }
    }
    else print(scanner.nextInt())
}