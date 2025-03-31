package com.arekalov.compmatlab2.ui

import com.arekalov.compmatlab2.data.common.MathConstants
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.Solution

sealed interface State {
    fun isCorrect(): Boolean

    data class SingleState(
        val equation: SingleEquation? = null,
        val equationsList: List<SingleEquation> = MathConstants.SINGLE_EQUATIONS_LIST,
        val a: Double? = null,
        val b: Double? = null,
        val epsilon: Double = 0.005,
        val method: Method = Method.SimpleIterations,
        val methodsList: List<Method> = MathConstants.SINGLE_METHOD_LIST,
        val solution: Solution? = null,
        val error: String? = null,
    ) : State {
        override fun isCorrect(): Boolean {
            return equation != null && a != null && b != null && solution != null && error == null
        }
    }

    data class SystemState(
        val equationFirst: String? = null,
        val equationSecond: String? = null,
        val equationsListFirst: List<SingleEquation> = MathConstants.SYSTEM_EQUATIONS_FIRST_LIST,
        val equationsListSecond: List<SingleEquation> = MathConstants.SYSTEM_EQUATIONS_SECOND_LIST,
        val x: Double? = null,
        val y: Double? = null,
        val epsilon: Double = 0.005,
        val method: Method = Method.Newton,
        val methodsList: List<Method> = MathConstants.SYSTEM_METHOD_LIST,
        val solution: Solution? = null,
        val error: String? = null,
    ) : State {
        override fun isCorrect(): Boolean {
            return equationFirst != null && equationSecond != null && x != null
                    && y != null && solution != null && error == null
        }
    }
}

enum class Mode {
    Single, System,
}

fun toDesmosExpression(a: Double?, b: Double?, equation: String?): String {
    val expressionEnd = if (a != null && b != null) {
        """\left\{$a\le x\le$b\right\}"""
    } else if (a != null) {
        """\left\{$a\le x\right\}"""
    } else if (b != null) {
        """\left\{x\le$b\right\}"""
    } else {
        ""
    }
    return equation + expressionEnd
}