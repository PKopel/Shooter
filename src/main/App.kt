package main

import main.data.StringData
import main.data.StyleData
import main.data.ViewData
import java.awt.GridLayout
import javax.swing.JFrame

object App : JFrame() {
    private val data = StyleData
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
        background = data.background
        layout = GridLayout(3, 1)
        add(start)
        add(settings)
        add(exit)
    }

    //companion object {
    @JvmStatic
    fun main(args: Array<String>) {
        run(App, 150, 150, StringData.appName)
    }
    //}
}
