package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.network.clearGraph
import com.arekalov.compmatlab2.network.initGraph
import com.arekalov.compmatlab2.network.setExpression
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div

@Page
@Composable
fun Desmos() {
    DesmosGraph()
}

@Composable
fun DesmosGraph() {
    val calculatorId = "calculator"
    Column {
        Div(attrs = {
            id(calculatorId)
            style {
                width(600.px)
                height(400.px)
            }
        })
        var a by remember { mutableStateOf(1) }
        LaunchedEffect(Unit) {
            initGraph()
        }

        LaunchedEffect(a) {
            setExpression("y=x^$a")
        }

        IconButton(onClick = { a += 1 }) {
            SpanText("Click for +1")
        }

        IconButton(onClick = { clearGraph()}) {
            SpanText("Clear")
        }
    }
}
