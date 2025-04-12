package com.arekalov.compmatlab2.data.systemsolvingmethods

import com.arekalov.compmatlab2.data.models.SystemSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SystemSolution
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private const val MAX_ITERATIONS = 100
private const val H = 1e-5 // Шаг для численного дифференцирования

// Функция для численного вычисления частной производной
private fun partialDerivative(f: (Double, Double) -> Double, x: Double, y: Double, isX: Boolean): Double {
    return if (isX) {
        (f(x + H, y) - f(x - H, y)) / (2 * H)
    } else {
        (f(x, y + H) - f(x, y - H)) / (2 * H)
    }
}

fun newtonSystem(params: SystemSolvingParams): Result<SystemSolution> = runCatching {
    var x = params.x ?: 0.0
    var y = params.y ?: 0.0
    var iterations = 0

    // Определяем функции системы
    val f1: (Double, Double) -> Double = { x, y -> 
        when (params.firstEquation.string) {
            "x+sin(y)=-0.4" -> x + sin(y) + 0.4
            "sin(y)+2x=2" -> sin(y) + 2 * x - 2
            "sin(x-1)+y=1.5" -> sin(x - 1) + y - 1.5
            else -> throw IllegalArgumentException("Неизвестное уравнение")
        }
    }

    val f2: (Double, Double) -> Double = { x, y ->
        when (params.secondEquation.string) {
            "2y-cos(x-1)=0.7" -> 2 * y - cos(x - 1) - 0.7
            "y+cos(x-1)=0.7" -> y + cos(x - 1) - 0.7
            "x-sin(y+1)=1" -> x - sin(y + 1) - 1
            else -> throw IllegalArgumentException("Неизвестное уравнение")
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
            throw ArithmeticException("Якобиан равен нулю, метод не может быть применен")
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

    throw ArithmeticException("Метод не сошелся за $MAX_ITERATIONS итераций")
} 