package main.display

import main.App
import main.back.AppData
import main.button
import main.run
import java.awt.FlowLayout
import javax.swing.JFrame

class SettingsView(app: App) : JFrame() {
    private val data = AppData
    private val theme = button("Theme", app.background) {

    }
    private val back = button("Return", data.theme) {
        run(app,300,100, "Shooter")
        dispose()
    }

    init {
        background = data.background
        layout = FlowLayout()
        add(theme)
        add(back)
    }
}