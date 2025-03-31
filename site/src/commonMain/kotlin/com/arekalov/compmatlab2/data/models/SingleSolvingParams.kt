package com.arekalov.compmatlab2.data.models

import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.ui.State
import com.arekalov.compmatlab2.ui.model.Method

data class SingleSolvingParams(
    val equation: SingleEquation,
    val a: Double?,
    val b: Double?,
    val epsilon: Double = 0.005,
    val method: Method,
)

fun State.SingleState.toSingleSolvingParams(): SingleSolvingParams {
    return SingleSolvingParams(
        equation = equation ?: SingleEquation.stub,
        a = a,
        b = b,
        epsilon = epsilon,
        method = method
    )
}
