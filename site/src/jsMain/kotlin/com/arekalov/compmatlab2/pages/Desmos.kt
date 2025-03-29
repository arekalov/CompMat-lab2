package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.*
import com.arekalov.compmatlab2.components.layouts.PageLayout
import com.arekalov.compmatlab2.components.widgets.EditText
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.components.widgets.StringDropDown
import com.arekalov.compmatlab2.network.clearGraph
import com.arekalov.compmatlab2.network.initGraph
import com.arekalov.compmatlab2.network.setExpression
import com.varabyte.kobweb.compose.dom.svg.Text
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun Desmos() {
    PageLayout(title = "CompMat-lab2") {
        DesmosGraph()
    }
}

@Composable
fun DesmosGraph() {
    val calculatorId = "calculator"
    Column {
        Div(attrs = {
            id(calculatorId)
            style {
                width(30.cssRem)
                height(20.cssRem)
            }
        })
        var eq by remember { mutableStateOf(1) }
        LaunchedEffect(Unit) {
            initGraph()
        }

        LaunchedEffect(eq) {
            setExpression("y=x^$eq")
        }

        IconButton(onClick = { eq += 1 }) {
            SpanText("Click for +1")
        }

        IconButton(onClick = { clearGraph() }) {
            SpanText("Clear")
        }

        Spacer()

        var a by remember { mutableStateOf("") }
        P(
            attrs = Modifier
                .color(Color.blue)
                .toAttrs()
        ) {
            Text("a = $a")
        }
        EditText(
            onInput = { a = it },
            hint = "Enter a"
        )
        StringDropDown(
            onSelect = {},
            options = listOf("y=x^2+x+2", "y=x^3+12x+2", "y=x^2", "y=xd")
        )
    }

}
