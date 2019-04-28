package main.back

import main.back.MapObject.Direction.*
import main.data.GameData
import main.data.GameData.objects
import main.intersection
import java.awt.Color

data class Missile(override var x: Int,
                   override var y: Int,
                   val direction: Direction = Up,
                   override var color: Color = Color.BLACK) : MapObject() {
    override val height: Int = 2
    override val width: Int = 2


    fun move(): Boolean {
        return when (direction) {
            Up -> {
                if (!objects.contains(Missile(x, y + 1))) {
                    y++
                    true
                } else {
                    GameData.missiles.remove(this)
                    false
                }
            }
            Down -> {
                if (!objects.contains(Missile(x, y - 1))) {
                    y--
                    true
                } else {
                    GameData.missiles.remove(this)
                    false
                }
            }
            Left -> {
                if (!objects.contains(Missile(x - 1, y))) {
                    x--
                    true
                } else {
                    GameData.missiles.remove(this)
                    false
                }
            }
            Right -> {
                if (!objects.contains(Missile(x + 1, y))) {
                    x++
                    true
                } else {
                    GameData.missiles.remove(this)
                    false
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MapObject) this.x..(this.x + this.width) intersection other.x..(other.x + other.width) != null &&
                this.y..(this.y + this.height) intersection other.y..(other.y + other.height) != null
        else false
    }
}