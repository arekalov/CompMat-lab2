package com.arekalov.compmatlab2.ui

import com.arekalov.compmatlab2.ui.model.Method
import com.arekalov.compmatlab2.ui.model.Mode

sealed interface Action

sealed class SingleAction : Action {
    data object Calculate : SingleAction()

    data class ChangeEquation(val equation: String) : SingleAction()

    data class ChangeA(val a: Double?) : SingleAction()

    data class ChangeB(val b: Double?) : SingleAction()

    data class ChangeMethod(val method: Method) : SingleAction()

    data class ChangeMode(val mode: Mode) : SingleAction()
}

sealed class SystemAction : Action {
    data object Calculate : SystemAction()

    data class ChangeFirstEquation(val equation: String) : SystemAction()

    data class ChangeSecondEquation(val equation: String) : SystemAction()

    data class ChangeX(val x: Double?) : SystemAction()

    data class ChangeY(val y: Double?) : SystemAction()

    data class ChangeMethod(val method: Method) : SystemAction()

    data class ChangeMode(val mode: Mode) : SystemAction()
}
