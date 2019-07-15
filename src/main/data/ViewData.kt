package main.data

import main.display.GameView
import main.display.MapView
import main.display.SettingsView

object ViewData {
    val game = MapView()
    var view = GameView()
    var settings = SettingsView()
}