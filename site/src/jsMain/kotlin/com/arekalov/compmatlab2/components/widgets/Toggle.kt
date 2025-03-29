package com.arekalov.compmatlab2.components.widgets

import androidx.compose.runtime.Composable
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes

@Composable
fun Toggle(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colorPalette = ColorPalettes.Monochrome,
        focusBorderColor = ColorMode.current.toSitePalette().brand.primary
    )
}
