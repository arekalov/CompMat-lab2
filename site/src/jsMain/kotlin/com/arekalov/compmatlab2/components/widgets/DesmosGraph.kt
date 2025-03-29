package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.common.CALCULATOR_DIV_ID
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div

@Composable
fun DesmosGraph(
    width: Float = 50f,
    height: Float = 30f,
) {
    Div(attrs = {
        id(CALCULATOR_DIV_ID)
        style {
            width(width.cssRem)
            height(height.cssRem)
        }
    })
}