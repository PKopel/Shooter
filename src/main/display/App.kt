package main.display

import main.back.Game
import main.back.level.Easy
import main.back.level.Hard
import main.back.level.Medium
import main.back.level.VeryHard
import main.data.StringData
import main.data.StringData.level
import main.data.StyleData
import main.data.ViewData
import java.awt.GridLayout
import javax.swing.JComboBox
import javax.swing.JFrame

class App : JFrame() {
    private val data = StyleData
    private val difficulty = JComboBox<String>(level)
    private val start = button(StringData.play, data.theme) {
        main.run(ViewData.view, 700, 750, StringData.appName)
        dispose()
    }
    private val settings = button(StringData.settings, data.theme) {
        main.run(ViewData.settings, 150, 150)
        dispose()
    }
    private val exit = button(StringData.exit, data.theme) {
        dispose()
    }

    init {
        difficulty.addActionListener {
            when ((it.source as JComboBox<*>).selectedItem) {
                level[0] -> Game.level = Easy
                level[1] -> Game.level = Medium
                level[2] -> Game.level = Hard
                level[3] -> Game.level = VeryHard
            }
            Game.reset()
        }
        difficulty.background = data.theme
        background = data.background
        layout = GridLayout(4, 1)
        add(difficulty)
        add(start)
        add(settings)
        add(exit)
    }

}
