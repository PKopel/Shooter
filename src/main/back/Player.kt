package main.back

import main.back.Game.damage
import main.back.Game.missiles
import main.back.Game.objects
import main.data.StyleData
import main.data.StyleData.pMissile
import main.intersection
import java.awt.Color
import java.awt.Graphics

data class Player(override var x: Int = 300,
                  override var y: Int = 300,
                  override val width: Int = 20,
                  override val height: Int = 20,
                  override var color: Color = StyleData.player) : MapObject() {
    override fun paint(g: Graphics) {
        g.color=color
        val d = Game.damage.toInt()
        g.drawOval(x + Game.shiftX, y + Game.shiftY, width, height)
        g.fillOval(x + Game.shiftX + d, y + Game.shiftY + d, width - 2 * d, height - 2 * d)
        val ix = when(direction){
            Direction.Left -> x
            Direction.Right -> x+width-6
            else -> x + width/2 - 3
        }
        val iy = when(direction){
            Direction.Down -> y
            Direction.Up -> y + height - 6
            else -> y + height/2 -3
         }
        g.color= StyleData.indicator
        g.fillRect(ix + Game.shiftX,iy + Game.shiftY,6,6)
    }

    var direction = Direction.Down

    fun moveLeft(): Boolean {
        return if (objects.contains(Obstacle(x - 10, y, width, height))) false
        else {
            x -= 10
            true
        }
    }

    fun moveRight(): Boolean {
        return if (objects.contains(Obstacle(x + 10, y, width, height))) false
        else {
            x += 10
            true
        }
    }

    fun moveUp(): Boolean {
        return if (objects.contains(Obstacle(x, y + 10, width, height))) false
        else {
            y += 10
            true
        }
    }

    fun moveDown(): Boolean {
        return if (objects.contains(Obstacle(x, y - 10, width, height))) false
        else {
            y -= 10
            true
        }
    }

    fun shoot() {
        synchronized(missiles) {
            missiles.put(Missile(x + width / 2, y + height / 2, direction, pMissile))
        }
    }

    fun hit() {
        damage+=0.5
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