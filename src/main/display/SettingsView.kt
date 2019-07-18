package main.display

import main.App
import main.back.Game
import main.data.StringData
import main.data.StyleData
import main.data.ViewData
import main.run
import java.awt.BorderLayout
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.*

class SettingsView : JFrame() {
    private val style = StyleData
    private val bar = JMenuBar()
    private val options = JMenu(StringData.opts[0])
    private val menus = arrayOf(
            JMenu(StringData.opts[1]),
            JMenu(StringData.opts[2]),
            JMenu(StringData.opts[3]),
            JMenu(StringData.opts[4])
    )

    private fun pListener(e: ActionEvent) {
        Game.objects.player.color = (e.source as JMenuItem).background
        for (radio in pColors) if (radio !== e.source) radio.isSelected = false
    }

    private fun oListener(e: ActionEvent) {
        style.obstacles = (e.source as JMenuItem).background
        for (radio in oColors) if (radio !== e.source) radio.isSelected = false
    }

    private fun sListener(e: ActionEvent) {
        style.shooters = (e.source as JMenuItem).background
        for (radio in sColors) if (radio !== e.source) radio.isSelected = false
    }

    private fun themeListener(e: ActionEvent) {
        when ((e.source as JMenuItem).text) {
            "Light" -> {
                style.theme = Color.LIGHT_GRAY
                style.background = Color.WHITE
            }
            "Dark" -> {
                style.theme = Color.GRAY
                style.background = Color.LIGHT_GRAY
            }
            "Yellow" -> {
                style.theme = Color.yellow
                style.background = Color.LIGHT_GRAY
            }
        }
        ViewData.view = GameView()
        ViewData.settings=SettingsView()
        for (radio in themes) if (radio !== e.source) radio.isSelected = false
    }

    private val pColors = arrayOf(
            radioMenuItem("Purple", getRGBColor(88,34,115), ::pListener),
            radioMenuItem("Blue", getRGBColor(23,45,153), ::pListener, true),
            radioMenuItem("Brown", getRGBColor(128,53,0), ::pListener)
    )

    private val oColors = arrayOf(
            radioMenuItem("Brown", getRGBColor(38,11,11), ::oListener),
            radioMenuItem("Green", getRGBColor(13,128,32), ::oListener, true),
            radioMenuItem("Grey", getRGBColor(77,77,77), ::oListener)
    )

    private val sColors = arrayOf(
            radioMenuItem("Red", getRGBColor(128,13,13), ::sListener, true),
            radioMenuItem("Orange", getRGBColor(255,128,0), ::sListener),
            radioMenuItem("Yellow", getRGBColor(179,165,18), ::sListener)
    )

    private val themes = arrayOf(
            radioMenuItem("Light", Color.LIGHT_GRAY, ::themeListener, true),
            radioMenuItem("Dark", Color.GRAY, ::themeListener),
            radioMenuItem("Yellow", Color.YELLOW, ::themeListener)
    )

    private val back = button(StringData.ret, style.theme) {
        run(App(), 150, 200, StringData.appName)
        dispose()
    }

    init {
        background = style.background
        bar.background = style.theme
        layout = GridLayout(3, 1)
        bar.add(BorderLayout.CENTER, options)
        for (item in pColors) menus[0].add(item)
        for (item in oColors) menus[1].add(item)
        for (item in sColors) menus[2].add(item)
        for (item in themes) menus[3].add(item)
        for (menu in menus) options.add(menu)
        add(bar)
        add(back)
    }
}