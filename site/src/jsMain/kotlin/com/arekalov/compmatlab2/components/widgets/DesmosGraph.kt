package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.common.CALCULATOR_DIV_ID
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div

@Composable
fun DesmosGraph() {
    Div(attrs = {
        id(CALCULATOR_DIV_ID)
        style {
            width(30.cssRem)
            height(20.cssRem)
        }
    })
}