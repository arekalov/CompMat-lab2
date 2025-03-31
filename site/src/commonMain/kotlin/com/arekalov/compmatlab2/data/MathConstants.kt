package com.arekalov.compmatlab2.data

import com.arekalov.compmatlab2.ui.model.Method

class MathConstants() {
    companion object {
        val SINGLE_EQUATIONS_LIST = listOf(
            "Select equation",
            "y=2.74x^3-1.93x^2-15.28x-3.72",
            "y=x^3-4.5x^2-9.21x-0.383",
            "y=x^3-4.81x^2-17.37x+5.38",
            "y=x^3-2.56x^2-1.325x+4.395"
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
