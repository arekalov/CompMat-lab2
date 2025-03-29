package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.components.widgets.*
import com.arekalov.compmatlab2.toSitePalette
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
fun InputSpecs() {
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
            text = "Enter function data:",
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
            labelText = "Equation:"
        )
        EditTextWithLabel(
            onInput = {},
            hint = "Enter int a",
            labelText = "a:",
        )
        EditTextWithLabel(
            onInput = {},
            hint = "Enter int b",
            labelText = "b:",
        )
        DropDownMenuWithLabel(
            onSelect = { sting -> },
            options = listOf("newton", "single", "iterations"),
            labelText = "Method:"
        )
        IconButton(
            onClick = {},
        ) {
            BorderBox {
                RegularText(
                    modifier = Modifier.outlineColor(Color.blue),
                    text = "Solve",
                )
            }
        }
    }
}
