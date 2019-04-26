package main

import main.display.GameView
import main.display.Settings
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.JFrame

object App : JFrame() {
    var theme = 0
        set(value) {
            background = when (value) {
                0 -> Color.LIGHT_GRAY
                1 -> Color.DARK_GRAY
                2 -> Color.GRAY
                else -> Color.LIGHT_GRAY
            }
            start.background = background
            settings.background = background
            exit.background = background
            field = value
        }
    private val start = button("Start", background) {
        run(GameView(this), 700, 700,"Shooter")
        dispose()
    }
    private val settings = button("Settings", background) {
        run(Settings(this), 300, 100)
        dispose()
    }
    private val exit = button("Exit", background) {
        dispose()
    }

    init {
        layout = FlowLayout()
        add(start)
        add(settings)
        add(exit)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        run(this, 300, 100, "Shooter")
    }
}