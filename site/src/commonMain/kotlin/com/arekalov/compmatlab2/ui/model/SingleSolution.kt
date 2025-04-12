package com.arekalov.compmatlab2.ui.model

sealed interface Solution

data class SingleSolution(
    val answer: Double? = null,
    val functionResult: Double? = null,
    val method: Method,
    val iterationsCount: Int? = null,
    val error: String? = null,
) : Solution {
    override fun toString(): String {
        return if (error != null) {
            "Error in $method method: $error"
        } else {
            "Solved by $method method \nanswer: $answer\nfunctionResult: $functionResult\niterationsCount: $iterationsCount"
        }
    }
}