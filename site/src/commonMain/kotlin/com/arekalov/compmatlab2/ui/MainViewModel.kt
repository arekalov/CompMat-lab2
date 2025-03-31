package com.arekalov.compmatlab2.ui

import androidx.lifecycle.ViewModel
import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.arekalov.compmatlab2.data.*
import com.arekalov.compmatlab2.ui.model.toDesmosExpression

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(State.SingleState(equation = MathConstants.SINGLE_EQUATIONS_LIST.first()))
    val state: StateFlow<State>
        get() = _state

    fun reduce(action: Action) {
        jsLog(action.toString())
        when (action) {
            is Action.Calculate -> onCalculateReceived()
            is Action.ChangeA -> onChangeAReceived(a = action.a)
            is Action.ChangeB -> onChangeBReceived(b = action.b)
            is Action.ChangeMethod -> onChangeMethod(method = action.method)
            is Action.ChangeEquation -> onChangeEquation(equation = action.equation)
            is Action.ChangeMode -> {}
        }
    }

    private fun onCalculateReceived() {

    }

    private fun onChangeAReceived(a: Double?) {
        _state.update { it.copy(a = a) }
        updateExpression()
    }

    private fun onChangeBReceived(b: Double?) {
        _state.update { it.copy(b = b) }
        updateExpression()
    }

    private fun onChangeMethod(method: Method) {
        _state.update { it.copy(method = method) }
    }

    private fun onChangeEquation(equation: String) {
        _state.update { it.copy(
            equation = equation,
            a = null,
            b = null,
            method = Method.SimpleIterations
        ) }
        when (val currentState = state.value) {
            is State.SingleState -> {
                setExpression(currentState.equation ?: "")
            }
            is State.SystemState -> {}
        }
    }

    private fun updateExpression() {
        when (val currentState = state.value) {
            is State.SingleState -> {
                setExpression(currentState.toDesmosExpression())
            }
            else -> {}
        }
    }
}
