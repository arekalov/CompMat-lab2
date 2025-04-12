package com.arekalov.compmatlab2.ui

import androidx.lifecycle.ViewModel
import com.arekalov.compmatlab2.data.FIRST_EQUATION
import com.arekalov.compmatlab2.data.SECOND_EQUATION
import com.arekalov.compmatlab2.data.common.MathConstants
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.data.jsLog
import com.arekalov.compmatlab2.data.models.toSingleSolvingParams
import com.arekalov.compmatlab2.data.models.toSystemSolvingParams
import com.arekalov.compmatlab2.data.setExpression
import com.arekalov.compmatlab2.data.singlesolvingmethods.halfDivisionMethod
import com.arekalov.compmatlab2.data.singlesolvingmethods.newtonsMethod
import com.arekalov.compmatlab2.data.singlesolvingmethods.singleSimpleIterationsMethod
import com.arekalov.compmatlab2.data.systemsolvingmethods.simpleIterationsSystem
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.SingleSolution
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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
            val result = halfDivisionMethod(params = singleState.value.toSingleSolvingParams())
            _singleState.update {
                it.copy(
                    solution = result.fold(
                        onSuccess = { it },
                        onFailure = { error ->
                            SingleSolution(
                                method = Method.HalfDivision,
                                error = error.message ?: "Unknown error"
                            )
                        }
                    )
                )
            }
        } else if (singleState.value.method == Method.SimpleIterations) {
            jsLog("SimpleIterations")
            val result = singleSimpleIterationsMethod(params = singleState.value.toSingleSolvingParams())
            _singleState.update {
                it.copy(
                    solution = result.fold(
                        onSuccess = { it },
                        onFailure = { error ->
                            SingleSolution(
                                method = Method.SimpleIterations,
                                error = error.message ?: "Unknown error"
                            )
                        }
                    )
                )
            }
        } else if (singleState.value.method == Method.Newton) {
            jsLog("Newton")
            val result = newtonsMethod(params = singleState.value.toSingleSolvingParams())
            _singleState.update {
                it.copy(
                    solution = result.fold(
                        onSuccess = { it },
                        onFailure = { error ->
                            SingleSolution(
                                method = Method.Newton,
                                error = error.message ?: "Unknown error"
                            )
                        }
                    )
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
                equation = singleState.value.equation?.desmos ?: singleState.value.equation?.string
            ), FIRST_EQUATION
        )
    }


    // For SystemState
    private fun onSystemCalculateReceived() {
        if (singleState.value.method == Method.SimpleIterations) {
            jsLog("SimpleIterations")
            _systemState.update {
                it.copy(
                    solution = simpleIterationsSystem(params = systemState.value.toSystemSolvingParams()).getOrNull()
                )
            }
        }
    }

    private fun onChangeFirstEquation(equation: SingleEquation) {
        _systemState.update {
            it.copy(equationFirst = equation)
        }
        updateSystemFirstExpression()
    }

    private fun onChangeSecondEquation(equation: SingleEquation) {
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
                equation = systemState.value.equationFirst?.desmos ?: "",
            ), FIRST_EQUATION
        )
    }

    private fun updateSystemSecondExpression() {
        setExpression(
            toDesmosExpression(
                a = systemState.value.x,
                b = systemState.value.y,
                equation = systemState.value.equationSecond?.desmos ?: "",
            ), SECOND_EQUATION
        )
    }
}

