package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.common.SINGLE_STR
import com.arekalov.compmatlab2.common.SYSTEM_STR
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.css.cssRem

@Composable
fun ToggleEqMode(
    modifier: Modifier = Modifier,
    systemSelected: Boolean,
    onChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        RegularText(
            modifier = Modifier.padding(right = 0.5.cssRem),
            text = SINGLE_STR,
        )
        Toggle(
            checked = systemSelected,
            onCheckedChange = onChange
        )
        RegularText(
            modifier = Modifier.padding(left = 0.5.cssRem),
            text = SYSTEM_STR
        )
    }
}
