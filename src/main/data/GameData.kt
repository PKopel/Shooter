package main.data

import main.back.MapObjectSet
import main.back.Missiles
import main.back.Player
import main.display.MapView
import kotlin.concurrent.timer

object GameData {

    val player = Player(300, 300, 20, 20)
    val objects = MapObjectSet()
    val missiles = Missiles()
    val game = MapView()

    private var bufferX = 0
    var shiftX = 0
        set(value) {
            if ((value < field && player.moveRight()) ||
                    (value > field && player.moveLeft())) {
                if (Math.abs(value - 250) < 750) {
                    if (bufferX == 0) field = value
                    else bufferX -= 10
                } else bufferX += 10
            }
        }
    private var bufferY = 0
    var shiftY = 0
        set(value) {
            if ((value < field && player.moveUp()) ||
                    (value > field && player.moveDown())) {
                if (Math.abs(value - 250) < 750) {
                    if (bufferY == 0) field = value
                    else bufferY -= 10
                } else bufferY += 10
            }
        }

    var playing = false

    init {

        var time = 0
        timer("Moves", true, period = 5) {
            if (playing) {
                synchronized(missiles) {
                    for (m in missiles) m.move()
                    if (time < 300) {
                        time += 5
                    } else {
                        for (s in objects.shooters) s.shoot()
                        time = 0
                    }
                    missiles.clean()
                }
                game.repaint()
            }
        }
    }
}
