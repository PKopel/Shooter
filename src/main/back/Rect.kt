package main.back

import main.contains
import main.intersection
import java.awt.Color

data class Rect(var x: Int, var y: Int, val width: Int, val height: Int,val color: Color= Color.ORANGE) {

    fun moveLeft() { x-=10}
    fun moveRight() { x+=10 }
    fun moveUp() { y+=10 }
    fun moveDown() { y-=10 }

    override fun equals(other: Any?): Boolean {
        return if(other is Rect)
                   /* (other.x..(other.x+other.width) contains this.x &&
              other.y..(other.y+other.height) contains this.y) ||
                    (this.x..(this.x+this.width) contains other.x &&
              this.y..(this.y+this.height) contains other.y) ||
                    (other.x..(other.x+other.width) contains this.x+this.width &&
              other.y..(other.y+other.height) contains this.y+this.height) ||
                    (this.x..(this.x+this.width) contains other.x+other.width &&
              this.y..(this.y+this.height) contains other.y+other.height)

                    */
            this.x..(this.x+this.width) intersection other.x..(other.x+other.width) != null &&
                    this.y..(this.y+this.height) intersection other.y..(other.y+other.height) != null
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