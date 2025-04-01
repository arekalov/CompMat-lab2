package com.arekalov.compmatlab2.data.systemsolvingmethods

import com.arekalov.compmatlab2.data.models.SystemSolvingParams
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SystemSolution
import kotlin.math.abs


private const val X = -1_000_000.0
private const val Y = 1_000_000.0
private const val MAX_ITERATIONS = 100
fun simpleIterationsSystem(params: SystemSolvingParams): Result<SystemSolution> = runCatching {
    var currentX = params.x ?: X
    var currentY = params.y ?: Y

    if (currentX == currentY) {
        throw IllegalArgumentException("Error: A and B are the same")
    }

    var iterations = 0
    var x = 1.0
    var y = 1.0
    var xPred = Double.MAX_VALUE

    for (i in 0..MAX_ITERATIONS) {
        val xNew = params.firstEquation.phi(y)
        val yNew = params.secondEquation.phi(x)
        val xDelta = abs(xNew - x)
        val yDelta = abs(yNew - y)
        x = xNew
        y = yNew
        iterations++
        if (xDelta < params.epsilon || yDelta < params.epsilon) {
            break
        }
    }

    SystemSolution(
        xAnswer = x,
        yAnswer = y,
        method = Method.SimpleIterations,
        iterationsCount = iterations,
    )
}
