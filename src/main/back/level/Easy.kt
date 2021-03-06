package main.back.level

import main.back.Game.missiles
import main.back.objects.Missile

object Easy : Level() {
    override val obsNum: Int
        get() = 100
    override val shtNum: Int
        get() = 15
    override val hpPool: Double
        get() = 15.0

    override fun shoot(x: Int, y: Int, dir: Double) {
        synchronized(missiles) {
            missiles.put(Missile(x, y, Math.PI / 2))
            missiles.put(Missile(x, y, Math.PI))
            missiles.put(Missile(x, y, Math.PI * 3 / 2))
            missiles.put(Missile(x, y, Math.PI * 2))
        }

    }
}