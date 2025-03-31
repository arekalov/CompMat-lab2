package com.arekalov.compmatlab2.components.sections.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arekalov.compmatlab2.common.INPUT_SPECS_STR
import com.arekalov.compmatlab2.common.SOLVE_BUTTON
import com.arekalov.compmatlab2.components.widgets.BoldRegularText
import com.arekalov.compmatlab2.components.widgets.BorderBox
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.components.widgets.RegularText
import com.arekalov.compmatlab2.components.widgets.ToggleEqMode
import com.arekalov.compmatlab2.toSitePalette
import com.arekalov.compmatlab2.ui.model.State
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
    onFirstEquationChanged: (String) -> Unit,
    onSecondEquationChanged: (String) -> Unit,
    onSolvedClicked: () -> Unit,
    onEquationChanged: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.5.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        val palette = ColorMode.current.toSitePalette()

        var isSingleMode by remember { mutableStateOf(false) }

        BoldRegularText(
            text = INPUT_SPECS_STR,
            color = palette.text,
        )
        ToggleEqMode(
            systemSelected = isSingleMode,
            onChange = {isChecked ->
                isSingleMode = isChecked
                onEquationChanged("")
                onFirstEquationChanged("")
                onSecondEquationChanged("")
            }
        )

        if (isSingleMode) {
            SystemStateInput(
                onXChange = onXChanged,
                onYChange = onYChanged,
                onFirstEquationChanged = onFirstEquationChanged,
                onSecondEquationChanged = onSecondEquationChanged,
                state = systemState,
            )
        } else {
            SingleStateInput(
                onAChange = onAChanged,
                onBChange = onBChanged,
                onEquationChanged = onEquationChanged,
                state = singleState,
            )
        }

        IconButton(
            onClick = onSolvedClicked,
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