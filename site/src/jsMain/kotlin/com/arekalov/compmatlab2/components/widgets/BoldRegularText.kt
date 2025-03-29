package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun BoldRegularText(
    text: String,
    fontSize: Double = 1.3,
    color: Color = ColorMode.current.toSitePalette().text,
    modifier: Modifier = Modifier
) {
    Div(
        attrs = modifier
            .color(color)
            .fontWeight(FontWeight.Bold)
            .fontSize(fontSize.cssRem)
            .toAttrs()
    ) {
        Text(text)
    }
}