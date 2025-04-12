package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val MAX_ITERATIONS = 100

fun newtonsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    val a = params.a ?: Double.MIN_VALUE
    val b = params.b ?: Double.MAX_VALUE
    
    if (a >= b) {
        throw IllegalArgumentException("Left bound must be less than right bound")
    }

    val f = params.equation.f
    val f_prime = params.equation.proizv

    // Проверяем, что функция меняет знак на концах интервала
    val fa = f(a)
    val fb = f(b)
    if (fa * fb > 0) {
        throw IllegalArgumentException("Function must have different signs at the ends of the interval")
    }

    // Выбираем начальное приближение
    var x = if (fa * f_prime(a) > 0) a else b

    var iterations = 0
    var xPrev: Double

    for (i in 0 until MAX_ITERATIONS) {
        val fx = f(x)
        val fx_prime = f_prime(x)

        if (fx_prime == 0.0) {
            throw ArithmeticException("Derivative is zero, cannot proceed")
        }

        xPrev = x
        x = x - fx / fx_prime

        // Проверяем, что x не выходит за пределы интервала
        if (x < a || x > b) {
            // Если вышли за пределы, используем метод половинного деления
            x = (a + b) / 2
        }

        // Критерии завершения итерационного процесса
        if (abs(x - xPrev) < params.epsilon || abs(fx) < params.epsilon) {
            return@runCatching SingleSolution(
                answer = x,
                functionResult = f(x),
                method = Method.Newton,
                iterationsCount = iterations + 1,
            )
        }

        iterations++
    }

    throw ArithmeticException("Failed to converge after $MAX_ITERATIONS iterations")
}
