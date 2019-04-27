package main.back

import main.data.GameData.objects
import main.data.StyleData
import main.intersection
import java.awt.Color

data class Player(override var x: Int,
                  override var y: Int,
                  override val width: Int,
                  override val height: Int,
                  override var color: Color = StyleData.player) : MapObject(){

    private var direction = Direction.Up

    fun moveLeft():Boolean {
        return if (objects.contains(Obstacle(x - 10, y, width, height))) false
        else {
            x -= 10
            direction = Direction.Left
            true
        }
    }

    fun moveRight(): Boolean {
        return if (objects.contains(Obstacle(x + 10, y, width, height))) false
        else {
            x += 10
            direction = Direction.Left
            true
        }
    }

    fun moveUp():Boolean {
        return if (objects.contains(Obstacle(x, y + 10, width, height))) false
        else {
            y += 10
            direction = Direction.Left
            true
        }
    }

    fun moveDown():Boolean {
        return if (objects.contains(Obstacle(x, y  - 10, width, height))) false
        else {
            y -= 10
            direction = Direction.Left
            true
        }
    }

    fun shoot(missiles: Missiles) {
        missiles.add(Missile(x, y, direction, Color.darkGray))
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