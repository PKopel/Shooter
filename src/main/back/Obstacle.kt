package main.back

import main.data.StyleData
import java.awt.Color
import java.awt.Graphics
import java.awt.image.ImageObserver

data class Obstacle(override var x: Int,
                    override var y: Int,
                    override val width: Int,
                    override val height: Int,
                    override var color: Color = StyleData.obstacles) : MapObject(){
    override fun paint(g: Graphics, observer: ImageObserver) {
        g.color=color
        g.fillRect(x + Game.shiftX, y + Game.shiftY, width, height)
    }

    override fun equals(other: Any?): Boolean {
        return if(other is MapObject) this.x..(this.x + this.width) intersection other.x..(other.x + other.width) != null &&
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