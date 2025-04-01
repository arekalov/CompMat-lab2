package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val A = -1_000_000.0
private const val B = 1_000_000.0
private const val MAX_ITERATIONS = 100
fun chordsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    var currentA = params.a ?: A
    var currentB = params.b ?: B

    if (currentB == currentA) {
        throw IllegalArgumentException("Error: A and B are the same")
    }

    val f = params.equation.f
    var iterations = 0
    var x = 0.0
    var xPred = Double.MAX_VALUE

    for (i in 0..MAX_ITERATIONS) {
        x = nextVal(currentA, currentB, f)

        if (abs(xPred - x) < params.epsilon) {
            break
        }

        iterations++

        // Определяем, које значение обновлять
        if (f(currentA) * f(x) < 0) {
            currentB = x
        } else {
            currentA = x
        }

        xPred = x
    }

    SingleSolution(
        answer = x,
        functionResult = f(x),
        method = Method.Chords,
        iterationsCount = iterations,
    )
}

private fun nextVal(a: Double, b: Double, f: (Double) -> Double): Double {
    return (a * f(b) - b * f(a)) / (f(b) - f(a))
}
