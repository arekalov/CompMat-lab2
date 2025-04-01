package com.arekalov.compmatlab2.data.models

import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.ui.State
import com.arekalov.compmatlab2.ui.model.Method

data class SystemSolvingParams(
    val firstEquation: SingleEquation,
    val secondEquation: SingleEquation,
    val x: Double?,
    val y: Double?,
    val epsilon: Double = 0.005,
    val method: Method,
)

fun State.SystemState.toSystemSolvingParams(): SystemSolvingParams {
    return SystemSolvingParams(
        firstEquation = equationFirst ?: SingleEquation.stub,
        secondEquation = equationSecond ?: SingleEquation.stub,
        x = x,
        y = y,
        epsilon = epsilon,
        method = method,
    )
}
