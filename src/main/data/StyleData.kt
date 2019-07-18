package main.data

import main.back.Game
import main.display.getRGBColor
import java.awt.Color

object StyleData {
    var player: Color = getRGBColor(23,45,153)
    var pMissile: Color = Color.RED
    var theme: Color = Color.LIGHT_GRAY
    var background: Color = Color.WHITE
    var obstacles: Color = getRGBColor(13,128,32)
        set(value) {
            for (o in Game.objects.obstacles) o?.color = value
            field = value
        }
    var shooters: Color = getRGBColor(128,13,13)
        set(value) {
            for (o in Game.objects.shooters) o?.color = value
            field = value
        }
}
