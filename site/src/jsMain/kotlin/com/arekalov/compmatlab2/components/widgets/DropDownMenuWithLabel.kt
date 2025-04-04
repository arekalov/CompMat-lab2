package com.arekalov.compmatlab2.components.widgets


import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.data.common.SingleEquation
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import org.jetbrains.compose.web.css.cssRem

@Composable
fun DropDownMenuWithLabel(
    onSelect: (String) -> Unit,
    options: List<SingleEquation>,
    labelText: String = "",
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 1.cssRem),
        modifier = modifier,
    ) {
        if (labelText.isNotBlank()) {
            RegularText(text = labelText)
        }
        SingleEquationDropDown(
            onSelect = onSelect,
            options = options,
        )
    }
}


@Composable
fun DropDownMenuStringWithLabel(
    onSelect: (String) -> Unit,
    options: List<String>,
    labelText: String = "",
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 1.cssRem),
        modifier = modifier,
    ) {
        if (labelText.isNotBlank()) {
            RegularText(text = labelText)
        }
        StringDropDown(
            onSelect = onSelect,
            options = options,
        )
    }
}