package main.display

import main.App
import main.button
import main.run
import java.awt.FlowLayout
import javax.swing.JFrame

class Settings(app: App) : JFrame() {
    private val theme = button("Theme", app.background) {
        app.theme = (app.theme + 1) % 3
        background = app.background
        repaint()
    }
    private val back = button("Return", app.background) {
        run(app,300,100, "Shooter")
        dispose()
    }

    init {
        background = app.background
        layout = FlowLayout()
        add(theme)
        add(back)
    }
}