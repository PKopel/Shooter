package main.back

import main.data.StringData
import main.data.ViewData.game
import main.data.ViewData.view
import kotlin.concurrent.timer

object GameData {

    fun reset() {
        damage = 0
        shiftY = 0
        shiftX = 0
        player = Player()
        bufferX = 0
        bufferY = 0
        objects.clear()
        objects.fillMap()
        playing = false
    }

    var player = Player()
    var damage = 0
        set(value) {
            field = value
            if (value >= 10) playing = false
            game.repaint()
        }
    val objects = MapObjectSet()
    val missiles = Missiles()
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
        set(value) {
            if (damage < 10) {
                if (value) view.play.text = StringData.stop
                else view.play.text = StringData.play
                field = value
            } else {
                view.play.text = StringData.lost
                field = false
            }
        }

    init {
        objects.fillMap()
        var time = 0
        timer(daemon = true, period = 5) {
            if (playing) {
                synchronized(missiles) {
                    for (m in missiles) m.move()
                    if (time < 300) {
                        time += 5
                    } else {
                        for (s in objects.shooters) s.shoot()
                        time = 0
                    }
                }
                game.repaint()
            }
        }
    }
}
