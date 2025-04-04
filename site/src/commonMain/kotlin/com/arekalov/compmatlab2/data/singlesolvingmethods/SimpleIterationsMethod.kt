package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val A = -1_000_000.0
private const val B = 1_000_000.0
private const val MAX_ITERATIONS = 100

fun singleIterationsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    var currentA = params.a ?: A
    var currentB = params.b ?: B

    if (currentB == currentA) {
        throw IllegalArgumentException("Error: A and B are the same")
    }

    var iterations = 0
    var xI = (currentA + currentB) / 2 // Средняя точка как начальное приближение
    val f = params.equation.f
    val phi = params.equation.phi

    for (i in 0..MAX_ITERATIONS) {
        val xIPlus1 = phi(xI)
        val delta = abs(xIPlus1 - xI)

        if (delta < params.epsilon) {
            break
        }

        xI = xIPlus1
        iterations++
    }

    SingleSolution(
        answer = xI,
        functionResult = f(xI),
        method = Method.SimpleIterations,
        iterationsCount = iterations,
    )
}

