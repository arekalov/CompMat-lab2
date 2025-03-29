package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLScriptElement

@Page
@Composable
fun Desmos() {
    DesmosGraph()
}

@Composable
fun DesmosGraph() {
    val calculatorId = "calculator"
    Div(attrs = {
        id(calculatorId)
        style {
            width(600.px)
            height(400.px)
        }
    })

    LaunchedEffect(Unit) {
        val script = document.createElement("script") as HTMLScriptElement
        script.src = "https://www.desmos.com/api/v1.10/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"
        script.onload = {
            val scriptInline = document.createElement("script") as HTMLScriptElement
            scriptInline.textContent = """
                var elt = document.getElementById('calculator');
                if (elt) {
                    var calculator = Desmos.GraphingCalculator(elt);
                    calculator.setExpression({id:'graph1', latex:'y=x^2'});
                }
            """.trimIndent()
            document.body?.appendChild(scriptInline)
        }
        document.body?.appendChild(script)
    }
}
