package com.arekalov.compmatlab2.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.arekalov.compmatlab2.common.PAGE_TITLE
import com.arekalov.compmatlab2.components.layouts.PageLayout
import com.arekalov.compmatlab2.components.sections.SingleStateInput
import com.arekalov.compmatlab2.components.sections.SystemStateInput
import com.arekalov.compmatlab2.components.widgets.BorderBox
import com.arekalov.compmatlab2.components.widgets.DesmosGraph
import com.arekalov.compmatlab2.data.initGraph
import com.arekalov.compmatlab2.ui.Action
import com.arekalov.compmatlab2.ui.MainViewModel
import com.arekalov.compmatlab2.ui.model.State
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun Index() {
    val viewModel by remember { mutableStateOf(MainViewModel()) }
    val state = viewModel.state.collectAsState().value

    val onAChanged = remember { { value: Double? -> viewModel.reduce(Action.ChangeA(value)) } }
    val onBChanged = remember { { value: Double? -> viewModel.reduce(Action.ChangeB(value)) } }
    val onEquationChanged = remember { { value: String -> viewModel.reduce(Action.ChangeEquation(value)) } }

    val onSolvedClicked = remember { { viewModel.reduce(Action.Calculate) } }


    PageLayout(
        title = PAGE_TITLE
    ) {
        LaunchedEffect(Unit) {
            initGraph(
                if (state is State.SingleState) {
                    state.equation ?: "y=x^2"
                } else {
                    "y=x^2"
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BorderBox {
                when (state) {
                    is State.SystemState -> {
                        SystemStateInput(
                            onSolveClicked = onSolvedClicked,
                            onXChange = { _ -> },
                            onYChange = { _ -> },
                            state = state
                        )
                    }

                    is State.SingleState -> {
                        SingleStateInput(
                            onAChange = onAChanged,
                            onBChange = onBChanged,
                            onSolveClicked = onSolvedClicked,
                            onEquationChanged = onEquationChanged,
                            state = state,
                        )
                    }
                }
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
