package processor

class Controller {
    var state = ProgramState.WAITING
    var firstMatrix: Matrix? = null
    var secondMatrix: Matrix? = null
    var constant: Double = 0.0
    private var transposeOperation: (() -> Unit)? = null
    private var mainState: ProgramOperation? = null

    init {
        writeAction()
    }

    fun processInput(input: String) {
        when (state) {
            ProgramState.WAITING -> chooseNextAction(input.toInt())
            else -> writeAction()
        }
    }

    private fun chooseNextAction(action: Int) {
        if (mainState == ProgramOperation.TRANSPOSE) {
            when (action) {
                1 -> {
                    transposeOperation =  fun() = firstMatrix!!.transposeMain()
                }
                2 -> {
                    transposeOperation = fun() = firstMatrix!!.transposeSide()
                }
                3 -> {
                    transposeOperation = fun() = firstMatrix!!.transposeVertical()
                }
                4 -> {
                    transposeOperation = fun() = firstMatrix!!.transposeHorizontal()
                }
                else -> {
                    state = ProgramState.WAITING
                    writeAction()
                }
            }
            state = ProgramState.WAIT_FOR_ONE_MATRICES
        } else {
            when (action) {
                1 -> {
                    mainState = ProgramOperation.ADD_MATRICES
                    state = ProgramState.WAIT_FOR_TWO_MATRICES
                }
                2 -> {
                    mainState = ProgramOperation.MULTIPLY_TO_CONSTANT
                    state = ProgramState.WAIT_FOR_ONE_MATRICES
                }
                3 -> {
                    mainState = ProgramOperation.MULTIPLY
                    state = ProgramState.WAIT_FOR_TWO_MATRICES
                }
                4 -> {
                    mainState = ProgramOperation.TRANSPOSE
                    state = ProgramState.WAITING
                    writeTransposeActions()
                }
                5 -> {
                    mainState = ProgramOperation.FIND_DETERMINANT
                    state = ProgramState.WAIT_FOR_ONE_MATRICES
                }
                6 -> {
                    mainState = ProgramOperation.INVERSE_MATRIX
                    state = ProgramState.WAIT_FOR_ONE_MATRICES
                }
                0 -> state = ProgramState.EXIT
            }
        }
    }

    private fun writeTransposeActions() {
        println("1. Main diagonal")
        println("2. Side diagonal")
        println("3. Vertical line")
        println("4. Horizontal line")
        println("Your choice: ")
    }

    private fun writeAction() {
        state = ProgramState.WAITING
        println("1. Add matrices")
        println("2. Multiply matrix to a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Calculate a determinant")
        println("6. Inverse matrix")
        println("0. Exit")
        println("Your choice: ")
    }

    fun processAction() {
        when (mainState) {
            ProgramOperation.ADD_MATRICES -> {
                addMatrices()
                writeAction()
            }
            ProgramOperation.MULTIPLY_TO_CONSTANT -> {
                if (state == ProgramState.WAIT_FOR_ONE_MATRICES)
                    state = ProgramState.WAIT_FOR_CONSTANT
                else {
                    multiply()
                    writeAction()
                }
            }
            ProgramOperation.MULTIPLY -> {
                multiplyMatrices()
                writeAction()
            }
            ProgramOperation.TRANSPOSE -> {
                transposeOperation!!()
                firstMatrix!!.print()

                mainState = null
                writeAction()
            }
            ProgramOperation.FIND_DETERMINANT -> {
                findDeterminant()
                writeAction()
            }
            ProgramOperation.INVERSE_MATRIX -> {
                firstMatrix!!.inverseMatrix()
                writeAction()
            }
        }
    }

    private fun multiply() {
        println("The multiplication result is:")
        val result = firstMatrix!! * constant
        result.print()
    }

    private fun multiplyMatrices() {
        println("The multiplication result is:")
        val result = firstMatrix!! * secondMatrix!!
        if (result == null) println("ERROR")
        else result.print()
    }

    private fun addMatrices() {
        println("The result is:")
        val result = firstMatrix!! + secondMatrix!!
        if (result == null) println("ERROR")
        else result.print()
    }

    private fun findDeterminant() {
        if (firstMatrix!!.rowsCount != firstMatrix!!.columnsCount)
            println("ERROR")
        else {
            println("The result is:")
            println(firstMatrix!!.findDeterminant())
        }
    }

    enum class ProgramOperation {
        ADD_MATRICES,
        MULTIPLY_TO_CONSTANT,
        MULTIPLY,
        TRANSPOSE,
        FIND_DETERMINANT,
        INVERSE_MATRIX
    }
}