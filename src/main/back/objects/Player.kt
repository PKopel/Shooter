package main.back.objects

import main.back.Game
import main.back.Game.scale
import main.back.Move
import main.back.intersection
import main.data.StyleData
import main.data.StyleData.pMissile
import java.awt.Color
import java.awt.Graphics
import kotlin.math.atan2

data class Player(override var x: Int = 300,
                  override var y: Int = 300,
                  override val width: Int = 20,
                  override val height: Int = 20,
                  override var color: Color = StyleData.player) : MapObject() {

    override fun paint(g: Graphics) {
        g.color = color
        val d = Game.damage.toInt()
        g.drawOval(((x + Move.shiftX) * scale).toInt(), ((y + Move.shiftY) * scale).toInt(),
                (width * scale).toInt(), (height * scale).toInt())
        g.fillOval(((x + Move.shiftX + d) * scale).toInt(), ((y + Move.shiftY + d) * scale).toInt(),
                ((width - 2 * d) * scale).toInt(), ((height - 2 * d) * scale).toInt())
    }

    fun shoot(xm: Int, ym: Int) {
        synchronized(Game.missiles) {
            val angle = atan2(-(y + Move.shiftY - ym / scale).toDouble(), (x + Move.shiftX - xm / scale).toDouble())
            Game.missiles.put(Missile(x + width / 2, y + height / 2, angle, pMissile))
        }
    }

    fun hit(): Boolean {
        Game.damage += 0.5
        return true
    }

    override fun equals(other: Any?): Boolean = super.equals(other)

    override fun hashCode(): Int = super.hashCode()

}