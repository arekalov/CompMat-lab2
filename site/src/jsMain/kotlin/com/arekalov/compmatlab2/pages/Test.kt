package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arekalov.compmatlab2.components.sections.InputSpecs
import com.arekalov.compmatlab2.components.widgets.DesmosGraph
import com.arekalov.compmatlab2.network.initGraph
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun Test() {
    LaunchedEffect(Unit) {
        initGraph()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        InputSpecs()
        DesmosGraph()
    }
}