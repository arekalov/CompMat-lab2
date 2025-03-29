package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.components.widgets.BoldRegularText
import com.arekalov.compmatlab2.components.widgets.DropDownMenuWithLabel
import com.arekalov.compmatlab2.components.widgets.EditTextWithLabel
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem

@Composable
fun InputSpecs() {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.cssRem),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val palette = ColorMode.current.toSitePalette()
        BoldRegularText(
            text = "Enter function data:",
            color = palette.text,
        )
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
    }
}
