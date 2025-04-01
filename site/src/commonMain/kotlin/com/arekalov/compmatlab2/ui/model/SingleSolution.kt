package com.arekalov.compmatlab2.ui.model

sealed interface Solution

data class SingleSolution(
    val answer: Double,
    val functionResult: Double,
    val method: Method,
    val iterationsCount: Int? = null,
) : Solution {
    override fun toString(): String {
        return "Sold by $method method \nanswer: $answer\nfunctionResult: $functionResult\niterationsCount: $iterationsCount"
    }
}