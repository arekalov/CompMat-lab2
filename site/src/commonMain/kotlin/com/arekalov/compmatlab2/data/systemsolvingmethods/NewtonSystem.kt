package com.arekalov.compmatlab2.data.systemsolvingmethods

import com.arekalov.compmatlab2.data.models.SystemSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SystemSolution
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

private const val MAX_ITERATIONS = 100
private const val H = 1e-5 // Шаг для численного дифференцирования
private const val DEFAULT_X = 1.0
private const val DEFAULT_Y = 1.0

// Функция для численного вычисления частной производной
private fun partialDerivative(f: (Double, Double) -> Double, x: Double, y: Double, isX: Boolean): Double {
    return if (isX) {
        (f(x + H, y) - f(x - H, y)) / (2 * H)
    } else {
        (f(x, y + H) - f(x, y - H)) / (2 * H)
    }
}

fun newtonSystem(params: SystemSolvingParams): Result<SystemSolution> = runCatching {
    var x = params.x ?: DEFAULT_X
    var y = params.y ?: DEFAULT_Y
    var iterations = 0

    // Определяем функции системы
    val f1: (Double, Double) -> Double = { x, y ->
        when (params.firstEquation.string) {
            "x+sin(y)=-0.4" -> x + sin(y) + 0.4
            "sin(y)+2x=2" -> sin(y) + 2 * x - 2
            "sin(x-1)+y=1.5" -> sin(x - 1) + y - 1.5
            "tg(xy)=x^2" -> tan(x * y) - x.pow(2)
            "sin(x+y)=1.5x-0.1" -> sin(x + y) - 1.5 * x + 0.1
            else -> throw IllegalArgumentException("Unexpected equation")
        }
    }

    val f2: (Double, Double) -> Double = { x, y ->
        when (params.secondEquation.string) {
            "2y-cos(x-1)=0.7" -> 2 * y - cos(x - 1) - 0.7
            "y+cos(x-1)=0.7" -> y + cos(x - 1) - 0.7
            "x-sin(y+1)=1" -> x - sin(y + 1) - 1
            "0.8x^2+2y^2=1" -> 0.8 * x.pow(2) + 2 * y.pow(2) - 1
            "x^2+y^2=1" -> x.pow(2) + y.pow(2) - 1
            else -> throw IllegalArgumentException("Unexpected equation")
        }
    }

    for (i in 0 until MAX_ITERATIONS) {
        // Вычисляем значения функций
        val f1_val = f1(x, y)
        val f2_val = f2(x, y)

        // Вычисляем частные производные
        val df1_dx = partialDerivative(f1, x, y, true)
        val df1_dy = partialDerivative(f1, x, y, false)
        val df2_dx = partialDerivative(f2, x, y, true)
        val df2_dy = partialDerivative(f2, x, y, false)

        // Вычисляем определитель матрицы Якоби
        val det = df1_dx * df2_dy - df1_dy * df2_dx

        if (abs(det) < 1e-10) {
            // Если якобиан близок к нулю, пробуем другое начальное приближение
            if (iterations == 0) {
                x = -DEFAULT_X
                y = -DEFAULT_Y
                continue
            }
            throw ArithmeticException("Jacobian is zero, the method cannot be applied")
        }

        // Вычисляем новые значения x и y
        val dx = (f1_val * df2_dy - f2_val * df1_dy) / det
        val dy = (f2_val * df1_dx - f1_val * df2_dx) / det

        x -= dx
        y -= dy

        iterations++

        // Проверяем условие сходимости
        if (abs(dx) < params.epsilon && abs(dy) < params.epsilon) {
            return@runCatching SystemSolution(
                xAnswer = x,
                yAnswer = y,
                method = Method.Newton,
                iterationsCount = iterations
            )
        }
    }

    throw ArithmeticException("The method did not converge in $MAX_ITERATIONS of iterations")
} 