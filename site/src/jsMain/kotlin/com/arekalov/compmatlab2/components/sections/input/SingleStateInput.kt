package com.arekalov.compmatlab2.components.sections.input

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.common.*
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
fun SingleStateInput(
    state: State.SingleState,
    onAChange: (Double?) -> Unit,
    onBChange: (Double?) -> Unit,
    onEquationChanged: (SingleEquation) -> Unit,
    onSingleEqMethodChanged: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.cssRem)
    ) {
        var a by remember { mutableStateOf("") }
        var b by remember { mutableStateOf("") }

        DropDownMenuWithLabel(
            onSelect = { value ->
                a = ""
                b = ""
                onEquationChanged(state.equationsList.find { it.string == value }!!)
            },
            options = state.equationsList.map { it.string },
            labelText = EQUATION_STR
        )
        EditTextWithLabel(
            text = a,
            onInput = { value ->
                onAChange(value.replace(oldChar = ',', newChar = '.').toDoubleOrNull())
                a = value
            },
            hint = ENTER_A_STR,
            labelText = A_LABEL_STR,
        )
        EditTextWithLabel(
            text = b,
            onInput = { value ->
                onBChange(value.replace(oldChar = ',', newChar = '.').toDoubleOrNull())
                b = value
            },
            hint = ENTER_B_STR,
            labelText = B_LABEL_STR,
        )
        DropDownMenuWithLabel(
            onSelect = onSingleEqMethodChanged,
            options = state.methodsList.map { it.name },
            labelText = METHOD_LABEL_STR
        )
    }
}
