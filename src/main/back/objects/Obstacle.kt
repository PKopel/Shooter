package main.back.objects

import main.back.Game.scale
import main.back.Move
import main.data.StyleData
import java.awt.Color
import java.awt.Graphics

data class Obstacle(override var x: Int,
                    override var y: Int,
                    override val width: Int,
                    override val height: Int,
                    override var color: Color = StyleData.obstacles) : MapObject() {
    override fun paint(g: Graphics) {
        g.color = color
        g.fillRect(((x + Move.shiftX) * scale).toInt(), ((y + Move.shiftY) * scale).toInt(),
                (width * scale).toInt(), (height * scale).toInt())
    }

    override fun equals(other: Any?): Boolean = super.equals(other)

    override fun hashCode(): Int = super.hashCode()

}