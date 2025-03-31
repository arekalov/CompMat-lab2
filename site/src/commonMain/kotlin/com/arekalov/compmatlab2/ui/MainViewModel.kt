package com.arekalov.compmatlab2.ui

import androidx.lifecycle.ViewModel
import com.arekalov.compmatlab2.ui.model.Method
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.arekalov.compmatlab2.data.*
import com.arekalov.compmatlab2.data.common.MathConstants
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.data.models.toSingleSolvingParams
import com.arekalov.compmatlab2.data.singlesolvingmethods.halfDivisionMethod
import com.arekalov.compmatlab2.data.singlesolvingmethods.singleIterationsMethod
import com.arekalov.compmatlab2.ui.model.Solution

class MainViewModel : ViewModel() {
    private val _singleState =
        MutableStateFlow(State.SingleState(equation = MathConstants.SINGLE_EQUATIONS_LIST.first()))
    val singleState: StateFlow<State.SingleState>
        get() = _singleState

    private val _systemState = MutableStateFlow(State.SystemState())
    val systemState: StateFlow<State.SystemState>
        get() = _systemState


    fun reduce(action: Action) {
        when (action) {
            is SingleAction -> when (action) {
                is SingleAction.Calculate -> onCalculateReceived()
                is SingleAction.ChangeA -> onChangeAReceived(a = action.a)
                is SingleAction.ChangeB -> onChangeBReceived(b = action.b)
                is SingleAction.ChangeMethod -> onChangeMethod(method = action.method)
                is SingleAction.ChangeEquation -> onChangeEquation(equation = action.equation)
                else -> {}
            }

            is SystemAction -> when (action) {
                is SystemAction.Calculate -> onSystemCalculateReceived()
                is SystemAction.ChangeFirstEquation -> onChangeFirstEquation(equation = action.equation)
                is SystemAction.ChangeSecondEquation -> onChangeSecondEquation(equation = action.equation)
                is SystemAction.ChangeX -> onChangeXReceived(x = action.x)
                is SystemAction.ChangeY -> onChangeYReceived(y = action.y)
                is SystemAction.ChangeMethod -> onChangeSystemMethod(method = action.method)
                else -> {}
            }
        }
    }

    // For SingleState
    private fun onCalculateReceived() {
        jsLog("onCalculateReceived")
        if (singleState.value.method == Method.HalfDivision) {
            jsLog("HalfDivision")
            _singleState.update {
                it.copy(
                    solution = halfDivisionMethod(params = singleState.value.toSingleSolvingParams()).getOrNull()
                )
            }
        } else if (singleState.value.method == Method.SimpleIterations) {
            jsLog("SimpleIterations")
            _singleState.update {
                it.copy(
                    solution = singleIterationsMethod(params = singleState.value.toSingleSolvingParams()).getOrNull()
                )
            }
        }

    }

    private fun onChangeAReceived(a: Double?) {
        _singleState.update { it.copy(a = a) }
        updateSingleExpression()
    }

    private fun onChangeBReceived(b: Double?) {
        _singleState.update { it.copy(b = b) }
        updateSingleExpression()
    }

    private fun onChangeMethod(method: String) {
        _singleState.update {
            it.copy(method = MathConstants.SINGLE_METHOD_LIST.find { it.name == method }
                ?: MathConstants.SINGLE_METHOD_LIST.first())
        }
    }

    private fun onChangeEquation(equation: SingleEquation) {
        _singleState.update {
            it.copy(
                equation = equation,
                a = null,
                b = null,
                method = Method.SimpleIterations
            )
        }
        updateSingleExpression()
    }

    private fun updateSingleExpression() {
        setExpression(
            toDesmosExpression(
                a = singleState.value.a,
                b = singleState.value.b,
                equation = singleState.value.equation?.string
            ), FIRST_EQUATION
        )
    }


    // For SystemState
    private fun onSystemCalculateReceived() {
        _systemState.update {
            it.copy(
                solution = Solution(
                    systemState.value.x ?: 0.0,
                    functionResult = systemState.value.y ?: 0.0,
                    iterationsCount = 0,
                    method = Method.HalfDivision,
                )
            )
        }
    }

    private fun onChangeFirstEquation(equation: String) {
        _systemState.update {
            it.copy(equationFirst = equation)
        }
        updateSystemFirstExpression()
    }

    private fun onChangeSecondEquation(equation: String) {
        _systemState.update {
            it.copy(equationSecond = equation)
        }
        updateSystemSecondExpression()
    }

    private fun onChangeXReceived(x: Double?) {
        _systemState.update { it.copy(x = x) }
        updateSystemFirstExpression()
        updateSystemSecondExpression()
    }

    private fun onChangeYReceived(y: Double?) {
        _systemState.update { it.copy(y = y) }
        updateSystemFirstExpression()
        updateSystemSecondExpression()
    }

    private fun onChangeSystemMethod(method: Method) {
        _systemState.update { it.copy(method = method) }
    }

    private fun updateSystemFirstExpression() {
        setExpression(
            toDesmosExpression(
                a = systemState.value.x,
                b = systemState.value.y,
                equation = systemState.value.equationFirst
            ), FIRST_EQUATION
        )
    }

    private fun updateSystemSecondExpression() {
        setExpression(
            toDesmosExpression(
                a = systemState.value.x,
                b = systemState.value.y,
                equation = systemState.value.equationSecond
            ), SECOND_EQUATION
        )
    }
}
