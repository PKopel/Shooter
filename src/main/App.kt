package main

import main.back.Game
import main.back.MapObjectSet
import main.data.StringData
import main.data.StringData.level
import main.data.StyleData
import main.data.ViewData
import main.display.button
import java.awt.GridLayout
import javax.swing.JComboBox
import javax.swing.JFrame

class App : JFrame() {
    private val data = StyleData
    private val difficulty = JComboBox<String>(level)
    private val start = button(StringData.play, data.theme) {
        run(ViewData.view, 700, 700, StringData.appName)
        dispose()
    }
    private val settings = button(StringData.settings, data.theme) {
        run(ViewData.settings, 150, 150)
        dispose()
    }
    private val exit = button(StringData.exit, data.theme) {
        dispose()
    }

    init {
        difficulty.addActionListener {
            when ((it.source as JComboBox<String>).selectedItem) {
                level[0] -> Game.objects = MapObjectSet(maxSht = 15)
                level[1] -> Game.objects = MapObjectSet(maxSht = 20)
                level[2] -> Game.objects = MapObjectSet(75, 25)
                level[3] -> Game.objects = MapObjectSet(50, 30)
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(App(), 150, 200, StringData.appName)
        }
    }
}
