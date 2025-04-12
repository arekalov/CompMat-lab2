package com.arekalov.compmatlab2.components.sections.input

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.common.*
import com.arekalov.compmatlab2.components.widgets.DropDownMenuStringWithLabel
import com.arekalov.compmatlab2.components.widgets.DropDownMenuWithLabel
import com.arekalov.compmatlab2.components.widgets.EditTextWithLabel
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.ui.State
import com.arekalov.compmatlab2.ui.model.Method
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.css.cssRem


@Composable
fun SystemStateInput(
    state: State.SystemState,
    onXChange: (Double?) -> Unit,
    onYChange: (Double?) -> Unit,
    onFirstEquationChanged: (SingleEquation) -> Unit,
    onSecondEquationChanged: (SingleEquation) -> Unit,
    onMethodChanged: (Method) -> Unit,
) {
    var x by remember { mutableStateOf("") }
    var y by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.cssRem)
    ) {
        DropDownMenuWithLabel(
            onSelect = { value ->
                x = ""
                y = ""
                onFirstEquationChanged(state.equationsListFirst.find { it.string == value }!!)
            },
            options = state.equationsListFirst,
            labelText = EQUATION_STR
        )
        DropDownMenuWithLabel(
            onSelect = { value ->
                x = ""
                y = ""
                onSecondEquationChanged(state.equationsListSecond.find { it.string == value }!!)
            },
            options = state.equationsListSecond,
            labelText = EQUATION_STR
        )
        EditTextWithLabel(
            text = x,
            onInput = { value ->
                onXChange(value.replace(oldChar = ',', newChar = '.').toDoubleOrNull())
                x = value
            },
            hint = ENTER_X_STR,
            labelText = X_LABEL_STR,
        )
        EditTextWithLabel(
            text = y,
            onInput = { value ->
                onYChange(value.replace(oldChar = ',', newChar = '.').toDoubleOrNull())
                y = value
            },
            hint = ENTER_Y_STR,
            labelText = Y_LABEL_STR,
        )
        DropDownMenuStringWithLabel(
            onSelect = { methodName -> 
                onMethodChanged(Method.valueOf(methodName))
            },
            options = state.methodsList.map { it.name },
            labelText = METHOD_LABEL_STR
        )
    }
}
