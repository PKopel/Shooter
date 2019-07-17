package main.back.level

import main.back.Game
import main.back.objects.Missile

object Hard: Level() {
    override val obsNum: Int
        get() = 100
    override val shtNum: Int
        get() = 15
    override val hpPool: Double
        get() = 15.0

    override fun shoot(x: Int, y: Int, dir: Double) {
        synchronized(Game.missiles) {
            Game.missiles.put(Missile(x, y, Math.PI / 2))
        }

    }
}