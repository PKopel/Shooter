package main

import main.data.StyleData
import main.display.GameView
import main.display.SettingsView
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JFrame

class App : JFrame() {
    private val data = StyleData
    private val start = button("Start", data.theme) {
        run(GameView(this), 700, 700, "Shooter")
        dispose()
    }
    private val settings = button("Settings", data.theme) {
        run(SettingsView(), 150, 150)
        dispose()
    }
    private val exit = button("Exit", data.theme) {
        dispose()
    }

    init {
        background = data.background
        layout = GridLayout(3,1)
        add(start)
        add(settings)
        add(exit)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(App(), 150, 150, "Shooter")
        }
    }
}