package com.arekalov.compmatlab2.ui.model

data class Solution(
    val answer: Double,
    val functionResult: Double,
    val method: Method,
    val iterationsCount: Int? = null,
) {
    override fun toString(): String {
        return "Sold by $method method \nanswer: $answer\nfunctionResult: $functionResult\niterationsCount: $iterationsCount"
    }
}