package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun OutlineTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = ColorMode.current.toSitePalette().text,
    outlineColor: Color = ColorMode.current.toSitePalette().brand.primary,
    onClick: () -> Unit,
) {
    Button(
        attrs = modifier
            .then(Modifier.border(0.1.cssRem, color = outlineColor))
            .then(Modifier.padding(0.5.cssRem))
            .toAttrs {
                onClick { onClick() }
            }
    ) {
        Text(text)
    }
}
