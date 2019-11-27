package main.back

import main.back.level.Easy
import main.back.level.Level
import main.back.objects.Missile
import main.data.StringData
import main.data.ViewData.game
import main.data.ViewData.view
import java.awt.BorderLayout
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.timer

object Game {

    var level: Level = Easy
        set(value) {
            field = value
            reset()
        }

    var objects = MapObjectSet()

    val missiles = LinkedBlockingQueue<Missile>()

    var damage = 0.0
        set(value) {
            field = value
            if (value >= level.hpPool) lose()
            game.repaint()
        }

    var scale = 1f

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

    fun won() {
        game.message.isVisible = true
        game.message.text = StringData.won
        game.add(BorderLayout.CENTER, game.message)
        view.play.isVisible = false
        playing = false
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun lose() {
        game.message.isVisible = true
        game.message.text = StringData.lost
        game.add(BorderLayout.CENTER, game.message)
        view.play.isVisible = false
        playing = false
    }

    fun reset() {
        damage = 0.0
        objects = MapObjectSet()
        missiles.clear()
        view.play.isVisible =true
        game.message.isVisible = false
        objects.fillMap()
        Move.reset()
        game.repaint()
        playing = false
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
                        for (s in objects.shooters) s?.shoot()
                        time = 0
                    }
                }
                game.repaint()
            }
        }
    }
}
