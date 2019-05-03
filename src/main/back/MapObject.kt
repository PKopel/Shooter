package main.back

import java.awt.Color
import java.awt.Graphics
import java.awt.image.ImageObserver

abstract class MapObject{
    abstract var x : Int
    abstract var y : Int
    abstract val height : Int
    abstract val width : Int
    abstract var color: Color
    abstract fun paint(g: Graphics, observer: ImageObserver)
    fun centerX() = x+width/2
    fun centerY() = y+height/2
}

