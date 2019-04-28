package main.back

import java.awt.Color

abstract class MapObject{
    abstract var x : Int
    abstract var y : Int
    abstract val height : Int
    abstract val width : Int
    abstract var color: Color
    enum class Direction{
        Up,Down,Left,Right
    }
}

