package com.arekalov.compmatlab2.data.common

data class SingleEquation(
    val string: String,
    val proizv: (Double) -> Double,
    val phi: (Double) -> Double,
    val f: (Double) -> Double,
) {
    companion object {
        val stub = SingleEquation("Select", proizv = { _ -> .0 }, phi = { _ -> .0 }) { _ -> .0 }
    }
}
