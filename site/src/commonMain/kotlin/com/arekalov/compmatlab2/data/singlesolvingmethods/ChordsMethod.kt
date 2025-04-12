package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlin.math.abs

private const val MAX_ITERATIONS = 100

fun newtonsMethod(params: SingleSolvingParams): Result<SingleSolution> = runCatching {
    var x = params.a ?: throw IllegalArgumentException("Initial guess must be provided")

    val f = params.equation.f
    val f_prime = params.equation.proizv

    var iterations = 0

    for (i in 0 until MAX_ITERATIONS) {
        val fx = f(x)
        val fx_prime = f_prime(x)

        if (fx_prime == 0.0) {
            throw ArithmeticException("Derivative is zero, cannot proceed")
        }

        val xNext = x - fx / fx_prime

        // Критерии завершения итерационного процесса
        if (abs(xNext - x) < params.epsilon || abs(fx) < params.epsilon || abs(fx / fx_prime) < params.epsilon) {
            return@runCatching SingleSolution(
                answer = xNext,
                functionResult = f(xNext),
                method = Method.Newton,
                iterationsCount = iterations + 1, // Учитываем текущую итерацию
            )
        }

        x = xNext
        iterations++
    }

    throw ArithmeticException("Failed to converge after $MAX_ITERATIONS iterations")
}
