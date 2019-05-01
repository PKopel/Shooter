package main.back

import main.contains
import main.back.Game.missiles
import main.data.StyleData
import main.intersection
import java.awt.Color
import java.awt.Graphics

data class Shooter(override var x: Int,
                   override var y: Int,
                   override val width: Int = 21,
                   override val height: Int = 21,
                   override var color: Color = StyleData.shooters) : MapObject() {
    override fun paint(g: Graphics) {
        g.color=color
        g.fillPolygon(this.xCoords(), this.yCoords(), 8)
    }

    private val visible: Boolean
        get() = -10..700 contains x + Game.shiftX &&
                -10..700 contains y + Game.shiftY

    fun xCoords(): IntArray = intArrayOf(
            x + Game.shiftX,
            x + width / 3 + Game.shiftX,
            x + 2 * width / 3 + Game.shiftX,
            x + width + Game.shiftX,
            x + width + Game.shiftX,
            x + 2 * width / 3 + Game.shiftX,
            x + width / 3 + Game.shiftX,
            x + Game.shiftX)

    fun yCoords(): IntArray = intArrayOf(
            y + height / 3 + Game.shiftY,
            y + Game.shiftY,
            y + Game.shiftY,
            y + height / 3 + Game.shiftY,
            y + 2 * height / 3 + Game.shiftY,
            y + height + Game.shiftY,
            y + height + Game.shiftY,
            y + 2 * height / 3 + Game.shiftY)

    fun shoot() {
        if (visible && height > 0)
            synchronized(missiles) {
                missiles.put(Missile(x + width / 2, y - 1, Direction.Down))
                missiles.put(Missile(x + width / 2, y + height, Direction.Up))
                missiles.put(Missile(x - 2, y + height / 2, Direction.Left))
                missiles.put(Missile(x + width, y + height / 2, Direction.Right))
            }
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