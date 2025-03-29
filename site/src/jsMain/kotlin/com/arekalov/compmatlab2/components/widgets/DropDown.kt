package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Option
import org.jetbrains.compose.web.dom.Select
import org.jetbrains.compose.web.dom.Text

@Composable
fun StringDropDown(
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Select(
        attrs = modifier
            .minWidth(5.cssRem)
            .padding(0.5.cssRem)
            .toAttrs {
                onInput { onSelect.invoke(it.value ?: "unexpected value") }
            }
    ) {
        options.forEach {
            Option(value = it) {
                Text(it)
            }
        }
    }
}