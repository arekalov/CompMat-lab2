package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val MAX_ITERATIONS = 100
private const val DEFAULT_INTERVAL_STEP = 1.0
private const val MAX_INTERVAL_SEARCH = 100

fun newtonsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    val f = params.equation.f
    val f_prime = params.equation.proizv

    // Если a и b не заданы, ищем подходящий интервал
    var a = params.a
    var b = params.b
    
    if (a == null || b == null) {
        // Начинаем поиск с 0
        var currentX = 0.0
        var step = DEFAULT_INTERVAL_STEP
        var iterations = 0
        
        // Ищем интервал, где функция меняет знак
        while (iterations < MAX_INTERVAL_SEARCH) {
            val fa = f(currentX)
            val fb = f(currentX + step)
            
            if (fa * fb <= 0) {
                a = currentX
                b = currentX + step
                break
            }
            
            // Если не нашли, увеличиваем интервал
            currentX += step
            step *= 2
            iterations++
        }
        
        // Если не нашли подходящий интервал, используем значения по умолчанию
        if (a == null || b == null) {
            a = -10.0
            b = 10.0
        }
    }

    // Выбираем начальное приближение
    var x = if (f(a) * f_prime(a) > 0) a else b
    var iterations = 0
    var xPrev: Double

    for (i in 0 until MAX_ITERATIONS) {
        val fx = f(x)
        val fx_prime = f_prime(x)

        if (fx_prime == 0.0) {
            // Если производная равна нулю, пробуем другое начальное приближение
            x = if (x == a) b else a
            continue
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