package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arekalov.compmatlab2.common.A_LABEL_STR
import com.arekalov.compmatlab2.common.B_LABEL_STR
import com.arekalov.compmatlab2.common.ENTER_A_STR
import com.arekalov.compmatlab2.common.ENTER_B_STR
import com.arekalov.compmatlab2.common.EQUATION_STR
import com.arekalov.compmatlab2.common.INPUT_SPECS_STR
import com.arekalov.compmatlab2.common.METHOD_LABEL_STR
import com.arekalov.compmatlab2.common.SOLVE_BUTTON
import com.arekalov.compmatlab2.components.widgets.BoldRegularText
import com.arekalov.compmatlab2.components.widgets.BorderBox
import com.arekalov.compmatlab2.components.widgets.DropDownMenuWithLabel
import com.arekalov.compmatlab2.components.widgets.EditTextWithLabel
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.components.widgets.RegularText
import com.arekalov.compmatlab2.components.widgets.ToggleEqMode
import com.arekalov.compmatlab2.toSitePalette
import com.arekalov.compmatlab2.ui.model.State
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.outlineColor
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.cssRem


@Composable
fun SystemStateInput(
    state: State.SystemState,
    onXChange: (Double) -> Unit,
    onYChange: (Double) -> Unit,
    onSolveClicked: () -> Unit,
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
        BoldRegularText(
            text = INPUT_SPECS_STR,
            color = palette.text,
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ToggleEqMode(
                systemSelected = checked,
            ) { newIsChecked ->
                checked = newIsChecked
            }
        }
        DropDownMenuWithLabel(
            onSelect = { sting -> },
            options = listOf("y=x^2+x+2", "y=x^3+12x+2", "y=x^2", "y=xd"),
            labelText = EQUATION_STR
        )
        DropDownMenuWithLabel(
            onSelect = { sting -> },
            options = listOf("y=x^2+x+2", "y=x^3+12x+2", "y=x^2", "y=xd"),
            labelText = EQUATION_STR
        )
        EditTextWithLabel(
            onInput = { onXChange(it.toDouble()) },
            hint = ENTER_A_STR,
            labelText = A_LABEL_STR,
        )
        EditTextWithLabel(
            onInput = { onYChange(it.toDouble()) },
            hint = ENTER_B_STR,
            labelText = B_LABEL_STR,
        )
        DropDownMenuWithLabel(
            onSelect = { sting -> },
            options = listOf("newton", "single", "iterations"),
            labelText = METHOD_LABEL_STR
        )
        IconButton(
            onClick = onSolveClicked,
        ) {
            BorderBox {
                RegularText(
                    modifier = Modifier.outlineColor(Color.blue),
                    text = SOLVE_BUTTON,
                )
            }
        }
    }
}
