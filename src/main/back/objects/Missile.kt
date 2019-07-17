package main.back.objects

import main.back.Game
import main.back.Game.objects
import main.back.Move
import main.back.contains
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
        g.fillOval(x + Move.shiftX, y + Move.shiftY, width, height)
    }

    private var dx = x.toDouble()
    private var dy = y.toDouble()
    override val height: Int = 4
    override val width: Int = 4
    private var range = 300

    @Suppress("ReplaceCallWithBinaryOperator")


    fun move(): Boolean {
        return if (range > 0 &&
                !objects.contains(Missile((dx - cos(angle)).toInt(), (dy - sin(angle)).toInt(), color = color))) {
            range--
            dx -= cos(angle)
            dy += sin(angle)
            x = dx.toInt()
            y = dy.toInt()
            true
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