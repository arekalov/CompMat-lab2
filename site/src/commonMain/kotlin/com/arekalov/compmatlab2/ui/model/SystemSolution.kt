package com.arekalov.compmatlab2.ui.model

data class SystemSolution(
    val xAnswer: Double,
    val yAnswer: Double,
    val method: Method,
    val iterationsCount: Int? = null,
) : Solution {
    override fun toString(): String {
        return "Sold by $method method \nx: $xAnswer \ny: $yAnswer \niterationsCount: $iterationsCount"
    }
}