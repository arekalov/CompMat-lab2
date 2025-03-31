package com.arekalov.compmatlab2.ui

import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.Mode

sealed class Action {
    data object Calculate : Action()

    data class ChangeEquation(val equation: String) : Action()

    data class ChangeA(val a: Double?) : Action()

    data class ChangeB(val b: Double?) : Action()

    data class ChangeMethod(val method: Method) : Action()

    data class ChangeMode(val mode: Mode) : Action()
}
