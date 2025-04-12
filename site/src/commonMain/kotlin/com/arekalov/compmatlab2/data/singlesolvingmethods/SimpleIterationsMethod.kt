package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.common.MathConstants
import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs


private const val MAX_ITERATIONS = 1000
private const val SEGMENTS = 100

fun derivative(x: Double, f: (Double) -> Double, h: Double = 1e-5): Double {
    return (f(x + h) - f(x - h)) / (2 * h)
}


fun singleSimpleIterationsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    val f = params.equation.f
    val phi = params.equation.phi


    val a = params.a ?: -100.0
    val b = params.b ?: 100.0
    val epsilon = params.epsilon

    // Оценка максимальной производной
    val maxDerivative = (0 until SEGMENTS)
        .map { i -> a + i * (b - a) / (SEGMENTS - 1) }.maxOfOrNull { x -> abs(derivative(x, f)) } ?: throw IllegalArgumentException("Не удалось оценить производную")

    var lambda = 1.0 / maxDerivative

    // Проверьте знак производной
    val isDerivPositive = (0 until SEGMENTS)
        .map { i -> a + i * (b - a) / (SEGMENTS - 1) }
        .all { x -> derivative(x, f) > 0 }

    if (isDerivPositive) {
        lambda *= -1
    }

    val customPhi = { x: Double -> x + lambda * f(x) }

    var x = a
    var iterations = 0

    while (iterations < MAX_ITERATIONS) {
        val xNext = customPhi(x)

        if (abs(xNext - x) < epsilon) {
            return@runCatching SingleSolution(
                answer = xNext,
                functionResult = f(xNext),
                method = Method.SimpleIterations,
                iterationsCount = iterations + 1
            )
        }

        x = xNext
        iterations++
    }

    throw IllegalArgumentException("Метод не сошелся за $MAX_ITERATIONS итераций")
}


