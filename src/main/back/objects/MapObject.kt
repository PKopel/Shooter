package main.back.objects

import main.back.intersection
import java.awt.Color
import java.awt.Graphics

abstract class MapObject {
    abstract var x: Int
    abstract var y: Int
    abstract val height: Int
    abstract val width: Int
    abstract var color: Color
    abstract fun paint(g: Graphics)
    override fun equals(other: Any?): Boolean {
        return if (other is MapObject) this.x..(this.x + this.width) intersection other.x..(other.x + other.width) != null &&
                this.y..(this.y + this.height) intersection other.y..(other.y + other.height) != null
        else false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + height
        result = 31 * result + width
        result = 31 * result + color.hashCode()
        return result
    }
}

