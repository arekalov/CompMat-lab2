package com.arekalov.compmatlab2.components.sections.input

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.common.*
import com.arekalov.compmatlab2.components.widgets.DropDownMenuWithLabel
import com.arekalov.compmatlab2.components.widgets.EditTextWithLabel
import com.arekalov.compmatlab2.toSitePalette
import com.arekalov.compmatlab2.ui.model.State
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem


@Composable
fun SystemStateInput(
    state: State.SystemState,
    onXChange: (Double?) -> Unit,
    onYChange: (Double?) -> Unit,
    onFirstEquationChanged: (String) -> Unit,
    onSecondEquationChanged: (String) -> Unit,

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
                onFirstEquationChanged(value)
            },
            options = state.equationsList,
            labelText = EQUATION_STR
        )
        DropDownMenuWithLabel(
            onSelect = { value ->
                x = ""
                y = ""
                onSecondEquationChanged(value)
            },
            options = state.equationsList,
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
        DropDownMenuWithLabel(
            onSelect = { sting -> },
            options = state.methodsList.map { it.name },
            labelText = METHOD_LABEL_STR
        )
    }
}
