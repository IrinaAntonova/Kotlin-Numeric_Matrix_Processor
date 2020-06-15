package processor

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.round

class Matrix(val rowsCount: Int, val columnsCount: Int) {
    private val cofactor: Matrix
        get() {
            val result = Matrix(rowsCount, columnsCount)
            for (i in 0 until rowsCount)
                for (j in 0 until columnsCount) {
                    result[i, j] = getDeterminantSign(i, j) * getMinor(i, j).findDeterminant()
                }
            return result
        }

    companion object {
        fun getDeterminantSign(i: Int): Int {
            return getDeterminantSign(i, 0)
        }

        fun getDeterminantSign(i: Int, j: Int): Int {
            return if ((i + j) % 2 == 0) 1 else -1
        }
    }

    private val matrix = Array(rowsCount) { DoubleArray(columnsCount) }

    operator fun get(row: Int, column: Int) = matrix[row][column]
    operator fun set(row: Int, column: Int, value: Double) {
        matrix[row][column] = if (value == -0.0) 0.0 else value
    }

    operator fun plus(other: Matrix): Matrix? {
        if (rowsCount != other.rowsCount || columnsCount != other.columnsCount)
            return null
        val result = Matrix(rowsCount, columnsCount)
        for (i in 0 until rowsCount)
            for (j in 0 until columnsCount)
                result[i, j] = this[i, j] + other[i, j]
        return result
    }

    operator fun times(scalar: Double): Matrix {
        val result = Matrix(rowsCount, columnsCount)
        for (i in 0 until rowsCount)
            for (j in 0 until columnsCount)
                result[i, j] = this[i, j] * scalar
        return result
    }

    operator fun times(other: Matrix): Matrix? {
        if (columnsCount != other.rowsCount)
            return null
        val result = Matrix(rowsCount, other.columnsCount)
        for (i in 0 until rowsCount)
            for (j in 0 until other.columnsCount) {
                for (k in 0 until columnsCount)
                    result[i, j] += this[i, k] * other[k, j]
            }
        return result
    }

    fun inverseMatrix() {
        if (rowsCount != columnsCount)
            println("ERROR")
        else {
            println("The result is:")
            val determinant = findDeterminant()
            if (determinant != 0.0) {
                val cofactor = cofactor
                cofactor.transposeMain()
                (cofactor * (1 / determinant)).print()
            } else println("ERROR")
        }
    }

    fun transposeMain() {
        for (i in 1 until rowsCount)
            for (j in 0 until i) {
                val temp = this[i, j]
                this[i, j] = this[j, i]
                this[j, i] = temp
            }
    }

    fun transposeSide() {
        for (i in 0 until rowsCount - 1)
            for (j in 0 until columnsCount - i) {
                val temp = this[i, j]
                this[i, j] = this[rowsCount - 1 - j, columnsCount - 1 - i]
                this[rowsCount - 1 - j, columnsCount - 1 - i] = temp
            }
    }

    fun transposeVertical() {
        for (i in 0 until rowsCount)
            for (j in 0 until columnsCount / 2) {
                val temp = this[i, j]
                this[i, j] = this[i, columnsCount - 1 - j]
                this[i, columnsCount - 1 - j] = temp
            }
    }

    fun transposeHorizontal() {
        for (i in 0 until rowsCount / 2)
            for (j in 0 until columnsCount) {
                val temp = this[i, j]
                this[i, j] = this[rowsCount - 1 - i, j]
                this[rowsCount - 1 - i, j] = temp
            }
    }

    fun findDeterminant(): Double {
        if (rowsCount == 1)
            return this[0, 0]
        if (rowsCount == 2)
            return this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]
        var result = 0.0
        for (i in 0 until this.columnsCount)
            result += getDeterminantSign(i) * this[0, i] * getMinor(0, i).findDeterminant()
        return result
    }

    fun print() {
        val sizes = IntArray(columnsCount)
        for (j in 0 until columnsCount)
            for (i in 0 until rowsCount)
                sizes[j] = max(sizes[j], getPrintElement(i, j).length)
        for (i in 0 until rowsCount) {
            for (j in 0 until columnsCount) {
                var strElement = getPrintElement(i, j)
                for (size in strElement.length..sizes[j])
                    strElement = " $strElement"
                print(strElement)
            }
            println()
        }
    }

    private fun getMinor(i: Int, j: Int): Matrix {
        val result = Matrix(rowsCount - 1, columnsCount - 1)
        for (_i in 0 until i)
            for (_j in 0 until j)
                result[_i, _j] = this[_i, _j]
        for (_i in i + 1 until this.rowsCount)
            for (_j in 0 until j)
                result[_i - 1, _j] = this[_i, _j]
        for (_i in i + 1 until this.rowsCount)
            for (_j in j + 1 until this.columnsCount)
                result[_i - 1, _j - 1] = this[_i, _j]
        for (_i in 0 until i)
            for (_j in j + 1 until this.columnsCount)
                result[_i, _j - 1] = this[_i, _j]
        return result
    }

    private fun getPrintElement(i: Int, j: Int): String {
        val strElement = this[i, j].toString()

        return when {
            this[i, j] == 0.0 -> "0"
            this[i, j] != floor(this[i, j]) -> (round(this[i, j] * 100) / 100).toString()
            strElement.contains('E') ->strElement
            else -> strElement.substring(0, strElement.lastIndex - 1)
        }
    }
}