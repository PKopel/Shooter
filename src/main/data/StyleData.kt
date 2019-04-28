package main.data

import java.awt.Color

object StyleData {
    var player: Color = Color.BLUE
    var theme: Color = Color.LIGHT_GRAY
    var background: Color = Color.WHITE
    var obstacles: Color = Color.ORANGE
        set(value) {
            for (o in GameData.objects.obstacles) o.color = value
            field = value
        }
    var shooters: Color = Color.RED
}
