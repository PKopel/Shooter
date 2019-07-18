package main.back.level

import main.back.Game
import main.back.objects.Missile

object Medium : Level() {
    override val obsNum: Int
        get() = 150
    override val shtNum: Int
        get() = 20
    override val hpPool: Double
        get() = 15.0

    override fun shoot(x: Int, y: Int, dir: Double) {
        synchronized(Game.missiles) {
            Game.missiles.put(Missile(x, y, Math.PI / 4))
            Game.missiles.put(Missile(x, y, Math.PI / 2))
            Game.missiles.put(Missile(x, y, Math.PI * 3 / 4))
            Game.missiles.put(Missile(x, y, Math.PI))
            Game.missiles.put(Missile(x, y, Math.PI * 5 / 4))
            Game.missiles.put(Missile(x, y, Math.PI * 3 / 2))
            Game.missiles.put(Missile(x, y, Math.PI * 7 / 4))
            Game.missiles.put(Missile(x, y, Math.PI * 2))
        }

    }
}