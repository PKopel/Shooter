package main.back

import main.back.Game.objects
import main.back.Game.player
import java.awt.Color
import java.awt.Graphics
import java.lang.Math.cos
import java.lang.Math.sin

data class Missile(override var x: Int,
                   override var y: Int,
                   private val angle: Double = 0.0,
                   override var color: Color = Color.BLACK) : MapObject() {
    override fun paint(g: Graphics) {
        g.color = color
        g.fillOval(x + Game.shiftX, y + Game.shiftY, width, height)
    }

    private var dx = x.toDouble()
    private var dy = y.toDouble()
    override val height: Int = 4
    override val width: Int = 4
    private var range = 300

    @Suppress("ReplaceCallWithBinaryOperator")
    private fun canMove(new: Missile): Boolean {
        return when {
            objects.contains(new) -> {
                Game.missiles.remove(this)
                false
            }
            new.equals(player) && this.color== Color.BLACK -> {
                player.hit()
                Game.missiles.remove(this)
                false
            }
            else -> true
        }
    }

    fun move(): Boolean {
        return if (range > 0) {
            range--
            if(canMove(Missile((dx - cos(angle)).toInt(),(dy - sin(angle)).toInt(),color = color))){
                dx-=cos(angle)
                dy+=sin(angle)
                x=dx.toInt()
                y=dy.toInt()
                true
            } else false

        } else {
            Game.missiles.remove(this)
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
        result = 31 * result + angle.toInt()
        result = 31 * result + height
        result = 31 * result + width
        return result
    }
}