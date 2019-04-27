package main.back

import main.data.StyleData
import main.intersection
import java.awt.Color

data class Rect(var x: Int, var y: Int, val width: Int, val height: Int, var color: Color = StyleData.obstacles) {

    fun moveLeft() {
        x -= 10
    }

    fun moveRight() {
        x += 10
    }

    fun moveUp() {
        y += 10
    }

    fun moveDown() {
        y -= 10
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Rect)
            this.x..(this.x + this.width) intersection other.x..(other.x + other.width) != null &&
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