package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.arekalov.compmatlab2.common.GITHUB_URI
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.network.Theme
import com.arekalov.compmatlab2.network.setTheme
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.cssRem

val NavHeaderStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .padding(1.cssRem)
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = {
        colorMode = colorMode.opposite
        if (colorMode.isLight) {
            setTheme(Theme.Light)
        } else {
            setTheme(Theme.Dark)
        }
    }) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Change theme", placement = PopupPlacement.BottomRight)
}

@Composable
fun NavHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = NavHeaderStyle.toModifier()
            .margin(left = 1.cssRem)
    ) {
        Link(GITHUB_URI) {
            Image("/compmat-logo.png", "CompMat-lab2", Modifier.height(3.cssRem).display(DisplayStyle.Block))
        }

        Spacer()
        Row(Modifier.gap(1.5.cssRem).displayIfAtLeast(Breakpoint.MD), verticalAlignment = Alignment.CenterVertically) {
            ColorModeButton()
        }
    }
}
