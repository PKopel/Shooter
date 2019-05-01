package main.back

import java.awt.Color
import java.awt.Graphics

abstract class MapObject{
    abstract var x : Int
    abstract var y : Int
    abstract val height : Int
    abstract val width : Int
    abstract var color: Color
    abstract fun paint(g: Graphics)
    enum class Direction{
        Up,Down,Left,Right;
        operator fun next(): Direction{
            return when(this){
                Up-> Right
                Right -> Down
                Down -> Left
                Left -> Up
            }
        }
    }
}

