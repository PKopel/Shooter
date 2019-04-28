package main.back

import main.data.GameData.damage
import main.data.GameData.missiles
import main.data.GameData.objects
import main.data.StyleData
import main.data.StyleData.pMissile
import main.intersection
import java.awt.Color

data class Player(override var x: Int = 300,
                  override var y: Int = 300,
                  override val width: Int = 20,
                  override val height: Int = 20,
                  override var color: Color = StyleData.player) : MapObject() {

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
        damage++
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