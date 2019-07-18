package main.back.objects

import java.awt.Color
import java.awt.Graphics

abstract class MapObject{
    abstract var x : Int
    abstract var y : Int
    abstract val height : Int
    abstract val width : Int
    abstract var color: Color
    abstract fun paint(g: Graphics)
}

