package com.arekalov.compmatlab2.network

import kotlinx.browser.document
import org.w3c.dom.HTMLScriptElement

const val DESMOS_API_URI = "https://www.desmos.com/api/v1.10/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"

private var calculator: dynamic = null

fun initGraph() {
    val script = document.createElement("script") as HTMLScriptElement
    script.src = DESMOS_API_URI
    script.onload = {
        val elt = document.getElementById("calculator")
        if (elt != null) {
            calculator = js(
                """
                new Desmos.GraphingCalculator(elt, {
                    invertedColors: true,
                    expressions: false,
                    settingsMenu: false
                })
            """
            )
        }
    }
    document.body?.appendChild(script)
}

fun clearGraph() {
    calculator?.setBlank()
}

fun setExpression(expression: String) {
    val expressionId = "graph1"
    calculator?.setExpression(js("{id: expressionId, latex: expression}"))
}
