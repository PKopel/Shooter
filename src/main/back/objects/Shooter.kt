package main.back.objects

import main.back.Move
import main.back.contains
import main.back.intersection
import main.data.StyleData
import java.awt.Color
import java.awt.Graphics

data class Shooter(override var x: Int,
                   override var y: Int,
                   override val width: Int = 21,
                   override val height: Int = 21,
                   override var color: Color = StyleData.shooters,
                   val shoot: (Int, Int, Double) -> Unit) : MapObject() {
    override fun paint(g: Graphics) {
        g.color = color
        g.fillPolygon(this.xCoords(), this.yCoords(), 8)
    }

    private val visible: Boolean
        get() = -10..700 contains x + Move.shiftX &&
                -10..700 contains y + Move.shiftY

    private fun xCoords(): IntArray = intArrayOf(
            x + Move.shiftX,
            x + width / 3 + Move.shiftX,
            x + 2 * width / 3 + Move.shiftX,
            x + width + Move.shiftX,
            x + width + Move.shiftX,
            x + 2 * width / 3 + Move.shiftX,
            x + width / 3 + Move.shiftX,
            x + Move.shiftX)

    private fun yCoords(): IntArray = intArrayOf(
            y + height / 3 + Move.shiftY,
            y + Move.shiftY,
            y + Move.shiftY,
            y + height / 3 + Move.shiftY,
            y + 2 * height / 3 + Move.shiftY,
            y + height + Move.shiftY,
            y + height + Move.shiftY,
            y + 2 * height / 3 + Move.shiftY)

    fun shoot() {
        if (visible && height > 0)
            shoot.invoke(x + width / 2, y + height / 2, Math.atan2((y - Move.y).toDouble(), (x - Move.x).toDouble()))
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