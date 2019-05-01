package main.back

import main.data.StringData
import main.data.StyleData
import main.data.ViewData
import main.data.ViewData.game
import main.data.ViewData.view
import java.awt.BorderLayout
import javax.swing.JTextArea
import kotlin.concurrent.timer

object Game {

    fun reset() {
        damage = 0.0
        shiftY = 0
        shiftX = 0
        player = Player(color = StyleData.player)
        bufferX = 0
        bufferY = 0
        objects.clear()
        missiles.clear()
        objects.fillMap()
        game.repaint()
        playing = false
    }

    var player = Player()
    var damage = 0.0
        set(value) {
            field = value
            if (value >= 10) lose()
            game.repaint()
        }
    var objects = MapObjectSet()
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

    fun won(){
        game.message.text=StringData.won
        game.add(BorderLayout.CENTER,game.message)
        view.play.isVisible=false
        playing=false
    }

    fun lose(){
        game.message.text=StringData.lost
        game.add(BorderLayout.CENTER,game.message)
        view.play.isVisible=false
        playing=false
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
