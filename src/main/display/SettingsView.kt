package main.display

import main.App
import main.button
import main.data.GameData.player
import main.data.StyleData
import main.radioMenuItem
import main.run
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class SettingsView : JFrame() {
    private val style = StyleData
    private val bar = JMenuBar()
    private val options = JMenu("Options")
    private val menus = arrayOf(
            JMenu("Player color"),
            JMenu("Obstacles color"),
            JMenu("Shooters color"),
            JMenu("Theme")
    )

    private fun pListener(e: ActionEvent) {
        player.color = (e.source as JMenuItem).background
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
                this@SettingsView.repaint()
            }
            "Dark" -> {
                style.theme = Color.GRAY
                style.background = Color.LIGHT_GRAY
                this@SettingsView.repaint()
            }
            "Yellow" -> {
                style.theme = Color.yellow
                style.background = Color.LIGHT_GRAY
                this@SettingsView.repaint()
            }
        }
        for (radio in themes) if (radio !== e.source) radio.isSelected = false
    }

    private val pColors = arrayOf(
            radioMenuItem("Cyan", Color.CYAN, ::pListener),
            radioMenuItem("Blue", Color.BLUE, ::pListener, true),
            radioMenuItem("Green", Color.GREEN, ::pListener),
            radioMenuItem("Yellow", Color.YELLOW, ::pListener)
    )

    private val oColors = arrayOf(
            radioMenuItem("Yellow", Color.YELLOW, ::oListener),
            radioMenuItem("Orange", Color.ORANGE, ::oListener, true),
            radioMenuItem("Grey", Color.GRAY, ::oListener),
            radioMenuItem("Magenta", Color.magenta, ::oListener)
    )

    private val sColors = arrayOf(
            radioMenuItem("Red", Color.RED, ::sListener),
            radioMenuItem("Black", Color.BLACK, ::sListener)
    )

    private val themes = arrayOf(
            radioMenuItem("Light", Color.LIGHT_GRAY, ::themeListener, true),
            radioMenuItem("Dark", Color.GRAY, ::themeListener),
            radioMenuItem("Yellow", Color.YELLOW, ::themeListener)
    )

    private val back = button("Return", style.theme) {
        run(App(), 150, 150, "Shooter")
        dispose()
    }

    init {
        background = style.background
        bar.background = style.theme
        layout = GridLayout(2, 1)
        bar.add(options)
        for (item in pColors) menus[0].add(item)
        for (item in oColors) menus[1].add(item)
        for (item in sColors) menus[2].add(item)
        for (item in themes) menus[3].add(item)
        for (menu in menus) options.add(menu)
        add(bar)
        add(back)
    }
}