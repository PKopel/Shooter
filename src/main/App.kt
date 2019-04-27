package main

import main.back.AppData
import main.display.GameView
import main.display.SettingsView
import java.awt.FlowLayout
import javax.swing.JFrame

object App : JFrame() {
    private val data = AppData
    private val start = button("Start", data.theme) {
        run(GameView(this), 700, 700,"Shooter")
        dispose()
    }
    private val settings = button("Settings", data.theme) {
        run(SettingsView(this), 300, 100)
        dispose()
    }
    private val exit = button("Exit", data.theme) {
        dispose()
    }

    init {
        background= data.background
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