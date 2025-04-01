package com.arekalov.compmatlab2.components.sections.input

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.common.INPUT_SPECS_STR
import com.arekalov.compmatlab2.common.SOLVE_BUTTON
import com.arekalov.compmatlab2.components.widgets.*
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.arekalov.compmatlab2.data.jsLog
import com.arekalov.compmatlab2.toSitePalette
import com.arekalov.compmatlab2.ui.State
import com.arekalov.compmatlab2.ui.model.Method
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem

@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    singleState: State.SingleState,
    systemState: State.SystemState,
    onAChanged: (Double?) -> Unit,
    onBChanged: (Double?) -> Unit,
    onXChanged: (Double?) -> Unit,
    onYChanged: (Double?) -> Unit,
    onFirstEquationChanged: (SingleEquation) -> Unit,
    onSecondEquationChanged: (SingleEquation) -> Unit,
    onSolvedClicked: () -> Unit,
    onEquationChanged: (SingleEquation) -> Unit,
    isSingleMode: Boolean,
    onSingleEqMethodChanged: (String) -> Unit,
    onSingleModeChanged: (Boolean) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.5.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        val palette = ColorMode.current.toSitePalette()

        BoldRegularText(
            text = INPUT_SPECS_STR,
            color = palette.text,
        )
        ToggleEqMode(
            systemSelected = isSingleMode,
            onChange = { isChecked ->
                onSingleModeChanged(!isChecked)
                onEquationChanged(SingleEquation.stub)
                onFirstEquationChanged(SingleEquation.stub)
                onSecondEquationChanged(SingleEquation.stub)
            }
        )

        if (isSingleMode) {
            SingleStateInput(
                onAChange = onAChanged,
                onBChange = onBChanged,
                onEquationChanged = onEquationChanged,
                state = singleState,
                onSingleEqMethodChanged = onSingleEqMethodChanged,
            )
        } else {
            SystemStateInput(
                onXChange = onXChanged,
                onYChange = onYChanged,
                onFirstEquationChanged = onFirstEquationChanged,
                onSecondEquationChanged = onSecondEquationChanged,
                state = systemState,
            )
        }

        IconButton(
            onClick = { onSolvedClicked(); jsLog("onSolvedClicked") },
            modifier = Modifier.margin(bottom = 1.cssRem)
        ) {
            BorderBox(
                color = ColorMode.current.toSitePalette().brand.primary
            ) {
                RegularText(
                    text = SOLVE_BUTTON,
                )
            }
        }
    }
}