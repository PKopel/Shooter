package main.back

import main.back.Game.damage
import main.back.Game.missiles
import main.back.Game.objects
import main.back.Game.shiftX
import main.back.Game.shiftY
import main.data.StyleData
import main.data.StyleData.pMissile
import java.awt.Color
import java.awt.Graphics
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO
import java.lang.Math.cos
import java.lang.Math.sin

data class Player(override var x: Int = 300,
                  override var y: Int = 300,
                  override val width: Int = 20,
                  override val height: Int = 20,
                  override var color: Color = StyleData.player) : MapObject() {

    private var image = ImageIO.read(File("graphics/standing.png"))
    var angle = 0.0
    private var dx = x.toDouble()
    private var dy = y.toDouble()
    override fun paint(g: Graphics, observer: ImageObserver) {
        val rotatedImage= rotate(image,-(angle+0.5*Math.PI))
        g.drawImage(rotatedImage,x + shiftX, y + shiftY,observer)
    }

    fun moveLeft(): Boolean {
        return if (
                objects.contains(
                        Obstacle((dx + cos(angle) *10).toInt(),
                                (dy + sin(angle) *10).toInt(),
                                width, height))) false
        else {
            dy+=sin(angle)*10
            dx+=cos(angle)*10
            shiftY-=(sin(angle)*10).toInt()
            shiftX-=(cos(angle)*10).toInt()
            x=dx.toInt()
            y=dy.toInt()
            true
        }
    }

    fun moveRight(): Boolean {
        return if (
                objects.contains(
                        Obstacle((dx - cos(angle)*10).toInt(),
                                (dy - sin(angle) *10).toInt(),
                                width, height))) false
        else {
            dx-= cos(angle) *10
            dy-= sin(angle) *10
            shiftY+=(sin(angle)*10).toInt()
            shiftX+=(cos(angle)*10).toInt()
            x=dx.toInt()
            y=dy.toInt()
            true
        }
    }

    fun moveDown(): Boolean {
        return if (
                objects.contains(
                        Obstacle((dx - sin(angle)*10).toInt(),
                                (dy + cos(angle)*10).toInt(),
                                width, height))) false
        else {
            dx-=sin(angle)*10
            dy+=cos(angle)*10
            shiftX+=(sin(angle)*10).toInt()
            shiftY-=(cos(angle)*10).toInt()
            x=dx.toInt()
            y=dy.toInt()
            true
        }
    }

    fun moveUp(): Boolean {
        return if (
                objects.contains(
                        Obstacle((dx + sin(angle)*10).toInt(),
                                (dy - cos(angle)*10).toInt(),
                                width, height))) false
        else {
            dx+=sin(angle)*10
            dy-=cos(angle)*10
            shiftX-=(sin(angle)*10).toInt()
            shiftY+=(cos(angle)*10).toInt()
            x=dx.toInt()
            y=dy.toInt()
            true
        }
    }

    fun shoot(xm: Int, ym: Int) {
        synchronized(missiles) {
            missiles.put(Missile(centerX(), centerY(),
                    angle( x + shiftX, xm,y + shiftY ,ym) , pMissile))
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