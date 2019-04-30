package main.back

import main.contains
import main.back.GameData.missiles
import main.data.StyleData
import main.intersection
import java.awt.Color

data class Shooter(override var x: Int,
                   override var y: Int,
                   override val width: Int = 21,
                   override val height: Int = 21,
                   override var color: Color = StyleData.shooters) : MapObject() {

    private val visible: Boolean
        get() = -10..700 contains x + GameData.shiftX &&
                -10..700 contains y + GameData.shiftY

    fun xCoords(): IntArray = intArrayOf(
            x + GameData.shiftX,
            x + width / 3 + GameData.shiftX,
            x + 2 * width / 3 + GameData.shiftX,
            x + width + GameData.shiftX,
            x + width + GameData.shiftX,
            x + 2 * width / 3 + GameData.shiftX,
            x + width / 3 + GameData.shiftX,
            x + GameData.shiftX)

    fun yCoords(): IntArray = intArrayOf(
            y + height / 3 + GameData.shiftY,
            y + GameData.shiftY,
            y + GameData.shiftY,
            y + height / 3 + GameData.shiftY,
            y + 2 * height / 3 + GameData.shiftY,
            y + height + GameData.shiftY,
            y + height + GameData.shiftY,
            y + 2 * height / 3 + GameData.shiftY)

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