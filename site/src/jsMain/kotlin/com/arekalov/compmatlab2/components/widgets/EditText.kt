package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Input

@Composable
fun EditText(
    onInput: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    Input(
        type = InputType.Text,
        attrs = modifier
            .padding(0.5.cssRem)
            .toAttrs{
                onInput { onInput(it.value) }
                placeholder(hint)
            }
    )
}