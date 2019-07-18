package main.back.objects

import main.back.Game
import main.back.Move
import main.back.intersection
import main.data.StyleData
import main.data.StyleData.pMissile
import java.awt.Color
import java.awt.Graphics

data class Player(override var x: Int = 300,
                  override var y: Int = 300,
                  override val width: Int = 20,
                  override val height: Int = 20,
                  override var color: Color = StyleData.player) : MapObject() {

    override fun paint(g: Graphics) {
        g.color = color
        val d = Game.damage.toInt()
        g.drawOval(x + Move.shiftX, y + Move.shiftY, width, height)
        g.fillOval(x + Move.shiftX + d, y + Move.shiftY + d, width - 2 * d, height - 2 * d)
    }

    fun shoot(xm: Int, ym: Int) {
        synchronized(Game.missiles) {
            val angle = Math.atan2(-(y + Move.shiftY - ym).toDouble(), (x + Move.shiftX - xm).toDouble())
            Game.missiles.put(Missile(x + width / 2, y + height / 2, angle, pMissile))
        }
    }

    fun hit(): Boolean {
        Game.damage += 0.5
        return true
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MapObject) this.x..(this.x + this.width) intersection other.x..(other.x + other.width) != null &&
                this.y..(this.y + this.height) intersection other.y..(other.y + other.height) != null
        else false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + color.hashCode()
        return result
    }

}