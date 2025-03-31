package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.common.*
import com.arekalov.compmatlab2.components.widgets.*
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
fun SingleStateInput(
    state: State.SingleState,
    onAChange: (Double?) -> Unit,
    onBChange: (Double?) -> Unit,
    onSolveClicked: () -> Unit,
    onEquationChanged: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.cssRem)
    ) {
        val palette = ColorMode.current.toSitePalette()

        var checked by remember { mutableStateOf(false) }
        var a by remember { mutableStateOf("") }
        var b by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(1.cssRem)
        ) {
            BoldRegularText(
                text = INPUT_SPECS_STR,
                color = palette.text,
            )
            ToggleEqMode(
                systemSelected = checked,
            ) { newIsChecked ->
                checked = newIsChecked
            }
        }
        DropDownMenuWithLabel(
            onSelect = { value ->
                a = ""
                b = ""
                onEquationChanged(value)
            },
            options = state.equationsList,
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
            onSelect = { sting -> },
            options = state.methodsList.map { it.name },
            labelText = METHOD_LABEL_STR
        )
        IconButton(
            onClick = onSolveClicked,
        ) {
            BorderBox(
                color = ColorMode.current.toSitePalette().brand.primary
            ) {
                RegularText(
                    text = SOLVE_BUTTON,
                )
            }
        }
    }
}
