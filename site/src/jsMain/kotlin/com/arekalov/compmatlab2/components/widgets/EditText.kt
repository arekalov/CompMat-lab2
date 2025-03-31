package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.cssRem


@Composable
fun EditText(
    text: String = "",
    onInput: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    Input(
        type = InputType.Text,
        value = text,
        onValueChange = onInput,
        placeholder = hint,
        focusBorderColor = ColorMode.current.toSitePalette().brand.primary,
        modifier = modifier
            .padding(0.5.cssRem)
    )
}