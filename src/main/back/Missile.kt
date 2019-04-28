package main.back

import main.back.MapObject.Direction.*
import main.contains
import main.data.GameData
import main.data.GameData.objects
import main.data.GameData.player
import java.awt.Color

data class Missile(override var x: Int,
                   override var y: Int,
                   val direction: Direction = Up,
                   override var color: Color = Color.BLACK) : MapObject() {
    override val height: Int = 2
    override val width: Int = 2
    private var range = 300

    @Suppress("ReplaceCallWithBinaryOperator")
    private fun canMove(new: Missile): Boolean {
        return when {
            objects.contains(new) -> {
                GameData.missiles.remove(this)
                false
            }
            new.equals(player) && this.color== Color.BLACK -> {
                player.hit()
                GameData.missiles.remove(this)
                false
            }
            else -> true
        }
    }

    fun move(): Boolean {
        return if (range > 0) {
            range--
            when (direction) {
                Down -> if (canMove(Missile(x, y - 1,color = color))) {
                    y--
                    true
                } else false
                Up -> if (canMove(Missile(x, y + 1,color = color))) {
                    y++
                    true
                } else false
                Left -> if (canMove(Missile(x - 1, y,color = color))) {
                    x--
                    true
                } else false
                Right ->
                    if (canMove(Missile(x + 1, y,color = color))) {
                        x++
                        true
                    } else false
            }
        } else {
            GameData.missiles.remove(this)
            false
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MapObject) other.x..(other.x + other.width) contains this.x + 1 &&
                other.y..(other.y + other.height) contains this.y + 1
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