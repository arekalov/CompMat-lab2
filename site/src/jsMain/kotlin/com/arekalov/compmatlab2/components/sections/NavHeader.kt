package com.arekalov.compmatlab2.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.arekalov.compmatlab2.common.CHANGE_THEME_STR
import com.arekalov.compmatlab2.common.GITHUB_URI
import com.arekalov.compmatlab2.common.PAGE_TITLE
import com.arekalov.compmatlab2.components.widgets.IconButton
import com.arekalov.compmatlab2.components.widgets.RegularText
import com.arekalov.compmatlab2.data.Theme
import com.arekalov.compmatlab2.data.setTheme
import com.arekalov.compmatlab2.toSitePalette
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
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
    Tooltip(ElementTarget.PreviousSibling, CHANGE_THEME_STR, placement = PopupPlacement.BottomRight)
}

@Composable
fun NavHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = NavHeaderStyle.toModifier()
            .margin(left = 1.cssRem)
    ) {
        Link(GITHUB_URI) {
            Image("/compmat-logo.png", PAGE_TITLE, Modifier.height(3.cssRem).display(DisplayStyle.Block))
        }

        RegularText(
            text = "CompMat-lab2",
            color = ColorMode.current.toSitePalette().brand.primary,
            fontSize = 1.5,
            modifier = Modifier.padding(leftRight = 1.cssRem)
        )

        Spacer()
        Row(Modifier.gap(1.5.cssRem).displayIfAtLeast(Breakpoint.MD), verticalAlignment = Alignment.CenterVertically) {
            ColorModeButton()
        }
    }
}
