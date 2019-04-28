package main.back

import main.back.MapObject.Direction.*
import main.contains
import main.data.GameData
import main.data.GameData.objects
import java.awt.Color

data class Missile(override var x: Int,
                   override var y: Int,
                   val direction: Direction = Up,
                   override var color: Color = Color.BLACK) : MapObject() {
    override val height: Int = 2
    override val width: Int = 2
    private var range = 300


    fun move(): Boolean {
        return if(range>0) {
            range--
            when (direction) {
                Up -> {
                    if (!objects.contains(Missile(x, y - 1))) {
                        y--
                        true
                    } else {
                        println(direction)
                        GameData.missiles.remove(this)
                        false
                    }
                }
                Down -> {
                    if (!objects.contains(Missile(x, y + 1))) {
                        y++
                        true
                    } else {
                        println(direction)
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
        } else {
            GameData.missiles.remove(this)
            false
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MapObject) other.x..(other.x + other.width) contains this.x+1 &&
                other.y..(other.y + other.height) contains this.y+1
        else false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + direction.hashCode()
        result = 31 * result + height
        result = 31 * result + width
        return result
    }
}