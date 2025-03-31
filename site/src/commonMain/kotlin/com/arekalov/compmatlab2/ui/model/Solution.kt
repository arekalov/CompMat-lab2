package com.arekalov.compmatlab2.ui.model

data class Solution(
    val answer: Double,
    val functionResult: Double,
    val method: Method,
    val iterationsCount: Int? = null,
)