import java.util.Scanner

// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val height = input.nextInt()
    val length1 = input.nextInt()
    val length2 = input.nextInt()
    val width = input.nextInt()
    val box1 = Box(height, length1, width)
    println(box1.toString())
    println(box1.copy(length = length2).toString())
}
