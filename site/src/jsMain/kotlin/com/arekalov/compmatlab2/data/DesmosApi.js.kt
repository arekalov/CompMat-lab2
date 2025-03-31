package com.arekalov.compmatlab2.data

import kotlinx.browser.document
import org.w3c.dom.HTMLScriptElement

const val DESMOS_API_URI = "https://www.desmos.com/api/v1.10/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6"

private var calculator: dynamic = null

actual fun initGraph(initEquation: String) {
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
                    settingsMenu: false,
                })
            """
            )
        }
    }
    document.body?.appendChild(script)
}

actual fun clearGraph() {
    calculator?.setBlank()
}

actual fun setExpression(expression: String) {
    val expressionId = "graph1"
    calculator?.setExpression(js("{id: expressionId, latex: expression}"))
}

internal fun setTheme(theme: Theme) {
    when (theme) {
        Theme.Dark -> {
            calculator.updateSettings(js("{'invertedColors': true}"))
        }

        Theme.Light -> {
            calculator.updateSettings(js("{'invertedColors': false}"))
        }
    }
}

internal enum class Theme {
    Dark, Light
}

actual fun jsLog(value: String) {
    js("console.log(value)")
}

