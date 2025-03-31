package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.common.MathConstants
import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.Solution
import kotlin.math.abs

private const val A = -1_000_000.0
private const val B = 1_000_000.0
private const val MAX_ITERATIONS = 100

fun singleIterationsMethod(params: SingleSolvingParams): Result<Solution> =
    runCatching {
        var currentA = params.a ?: A
        var currentB = params.b ?: B
        if (currentB == currentA) {
            throw Exception("Error: A and B is similar")
        }
        var iterations = 0
        var delta = 1.0
        var xI = currentB
        val f = params.equation.f
        val phi = params.equation.phi
        for (i in 0..MAX_ITERATIONS) {
            val xIPlus1 = phi(xI)
            val fiXPlus1 = phi(xIPlus1)
            val yXplus1 = f(xIPlus1)
            delta = abs(xIPlus1 - xI)
            if (delta < params.epsilon) {
                break
            }
            xI = xIPlus1
            iterations++
        }
        Solution(
            answer = xI,
            functionResult = f(xI),
            method = Method.SimpleIterations,
            iterationsCount = iterations,
        )
    }
