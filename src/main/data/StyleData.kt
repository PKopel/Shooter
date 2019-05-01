package main.data

import main.back.Game
import java.awt.Color

object StyleData {
    var indicator: Color = Color.RED
    var player: Color = Color.BLUE
    var pMissile: Color = Color.RED
    var theme: Color = Color.LIGHT_GRAY
    var background: Color = Color.WHITE
    var obstacles: Color = Color.ORANGE
        set(value) {
            for (o in Game.objects.obstacles) o.color = value
            field = value
        }
    var shooters: Color = Color.RED
        set(value) {
            for (o in Game.objects.shooters) o.color = value
            field = value
        }
}
