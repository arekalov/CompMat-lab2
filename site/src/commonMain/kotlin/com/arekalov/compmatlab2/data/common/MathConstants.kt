package com.arekalov.compmatlab2.data.common

import com.arekalov.compmatlab2.ui.model.Method
import kotlin.math.pow
import kotlin.math.sin

class MathConstants() {
    companion object {
        val SINGLE_EQUATIONS_LIST = listOf(
            SingleEquation.stub,
            SingleEquation(string = "y=2.74x^3-1.93x^2-15.28x-3.72") { x ->
                2.74 * x.pow(3) - 1.93 * x.pow(2) - 15.28 * x - 3.72
            },
            SingleEquation(string = "y=x^3-4.5x^2-9.21x-0.383") { x ->
                x.pow(3) - 4.5 * x.pow(2) - 9.21 * x - 0.383
            },
            SingleEquation(string = "y=x^3-2.56x^2-1.325x+4.395") { x ->
                x.pow(3) - 2.56 * x.pow(2) - 1.325 * x + 4.395
            },
            SingleEquation("y=1.23\\sin\\left(x+1\\right)") { x ->
                1.23 * sin(x + 1)
            }
        )

        val SYSTEM_EQUATIONS_LIST = listOf(
            "Select equation",
            "y=x^3-4.5x^2-9.21x-0.383",
            "y=x^3-4.81x^2-17.37x+5.38",
            "y=x^3-2.56x^2-1.325x+4.395"
        )

        val SINGLE_METHOD_LIST =
            listOf<Method>(Method.Chords, Method.Newton, Method.SimpleIterations, Method.HalfDivision)

        val SYSTEM_METHOD_LIST = listOf<Method>(Method.SimpleIterations, Method.Newton)
    }
}