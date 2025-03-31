package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.arekalov.compmatlab2.common.PAGE_TITLE
import com.arekalov.compmatlab2.components.layouts.PageLayout
import com.arekalov.compmatlab2.components.sections.input.InputForm
import com.arekalov.compmatlab2.components.widgets.BorderBox
import com.arekalov.compmatlab2.components.widgets.DesmosGraph
import com.arekalov.compmatlab2.data.initGraph
import com.arekalov.compmatlab2.ui.SingleAction
import com.arekalov.compmatlab2.ui.MainViewModel
import com.arekalov.compmatlab2.ui.SystemAction
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun Index() {
    val viewModel by remember { mutableStateOf(MainViewModel()) }
    val singleState = viewModel.singleState.collectAsState().value
    val systemState = viewModel.systemState.collectAsState().value

    val onAChanged = remember { { value: Double? -> viewModel.reduce(SingleAction.ChangeA(value)) } }
    val onBChanged = remember { { value: Double? -> viewModel.reduce(SingleAction.ChangeB(value)) } }
    val onEquationChanged = remember { { value: String -> viewModel.reduce(SingleAction.ChangeEquation(value)) } }

    val onXChanged = remember { { value: Double? -> viewModel.reduce(SystemAction.ChangeX(value)) } }
    val onYChanged = remember { { value: Double? -> viewModel.reduce(SystemAction.ChangeY(value)) } }
    val onFirstEquationChanged =
        remember { { value: String -> viewModel.reduce(SystemAction.ChangeFirstEquation(value)) } }
    val onSecondEquationChanged =
        remember { { value: String -> viewModel.reduce(SystemAction.ChangeSecondEquation(value)) } }

    val onSolvedClicked = remember { { viewModel.reduce(SingleAction.Calculate) } }

    PageLayout(
        title = PAGE_TITLE
    ) {
        LaunchedEffect(Unit) {
            initGraph()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            BorderBox {
                InputForm(
                    singleState = singleState,
                    systemState = systemState,
                    onAChanged = onAChanged,
                    onBChanged = onBChanged,
                    onSolvedClicked = onSolvedClicked,
                    onEquationChanged = onEquationChanged,
                    onFirstEquationChanged = onFirstEquationChanged,
                    onSecondEquationChanged = onSecondEquationChanged,
                    onXChanged = onXChanged,
                    onYChanged = onYChanged,
                )
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
