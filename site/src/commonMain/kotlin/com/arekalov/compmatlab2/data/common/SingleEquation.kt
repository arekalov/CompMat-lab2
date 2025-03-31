package com.arekalov.compmatlab2.data.common

data class SingleEquation(
    val string: String,
    val f: (Double) -> Double,
) {
    companion object {
        val stub = SingleEquation("Select") { _ -> .0 }
    }
}
