package main.back.level

import main.back.Game
import main.back.Move
import main.back.objects.Missile

object VeryHard : Level() {
    override val obsNum: Int
        get() = 150
    override val shtNum: Int
        get() = 25
    override val hpPool: Double
        get() = 25.0

    override fun shoot(x: Int, y: Int, dir: Double) {
        synchronized(Game.missiles) {
            val angle = Math.atan2((y - Move.y).toDouble(), (x - Move.x).toDouble())
            Game.missiles.put(Missile(x, y, -angle - Math.PI / 8))
            Game.missiles.put(Missile(x, y, -angle))
            Game.missiles.put(Missile(x, y, -angle + Math.PI / 8))
        }

    }
}