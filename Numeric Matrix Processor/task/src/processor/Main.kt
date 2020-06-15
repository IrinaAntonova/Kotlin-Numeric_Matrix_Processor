package processor

import java.util.*

private val scanner = Scanner(System.`in`)
fun main() {

    val controller = Controller()

    while (controller.state != ProgramState.EXIT) {
        when (controller.state) {
            ProgramState.WAITING -> controller.processInput(scanner.next())
            ProgramState.WAIT_FOR_TWO_MATRICES -> {
                controller.firstMatrix = createMatrix("first")
                controller.secondMatrix = createMatrix("second")

                controller.processAction()
            }
            ProgramState.WAIT_FOR_ONE_MATRICES -> {
                controller.firstMatrix = createMatrix("first")

                controller.processAction()
            }
            ProgramState.WAIT_FOR_CONSTANT -> {
                controller.constant = createConstant()

                controller.processAction()
            }
        }
    }
}

fun createConstant(): Double {
    println("Enter constant: ")
    return scanner.nextDouble()
}

fun createMatrix(matrixOrder: String): Matrix {
    println("Enter size of $matrixOrder matrix: ")
    val matrix = Matrix(scanner.nextInt(), scanner.nextInt())
    println("Enter $matrixOrder matrix:")
    for (i in 0 until matrix.rowsCount)
        for (j in 0 until matrix.columnsCount)
            matrix[i, j] = scanner.nextDouble()
    return matrix
}