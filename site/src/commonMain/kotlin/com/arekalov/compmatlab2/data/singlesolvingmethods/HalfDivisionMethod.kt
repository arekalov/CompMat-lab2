package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val A = -1_000_000.0
private const val B = 1_000_000.0
private const val MAX_ITERATIONS = 100

fun halfDivisionMethod(params: SingleSolvingParams): Result<SingleSolution> =
    runCatching {
        var currentA = params.a ?: A
        var currentB = params.b ?: B
        if (currentB == currentA) {
            throw Exception("Error: A and B is similar")
        }
        var delta = 1.0
        var n = 0
        var x = 0.0
        val f = params.equation.f
        for (i in 0..MAX_ITERATIONS) {
            x = nextVal(currentA, currentB)
            val fa = f(currentA)
            val fb = f(currentB)
            val fx = f(x)

            delta = abs(currentA - currentB)
            if (delta < params.epsilon) {
                break
            }
            n++

            if (fa * fx > 0) {
                currentA = x
            } else {
                currentB = x
            }
        }
        SingleSolution(
            answer = x,
            functionResult = f(x),
            method = Method.HalfDivision,
            iterationsCount = n,
        )
    }

private fun nextVal(a: Double, b: Double): Double {
    return (a + b) / 2
}
