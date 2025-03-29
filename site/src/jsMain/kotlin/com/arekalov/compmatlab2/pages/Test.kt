package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arekalov.compmatlab2.components.layouts.PageLayout
import com.arekalov.compmatlab2.components.sections.InputSpecs
import com.arekalov.compmatlab2.components.widgets.BorderBox
import com.arekalov.compmatlab2.components.widgets.DesmosGraph
import com.arekalov.compmatlab2.network.initGraph
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun Test() {
    PageLayout(
        title = "CompMat-lab2"
    ) {
        LaunchedEffect(Unit) {
            initGraph()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BorderBox {
                InputSpecs()
            }
            DesmosGraph(
                width = 60f,
                height = 40f,
            )
            BorderBox {
                Text("fdf")
            }
        }
    }
}