package main.data

import main.back.Board
import main.back.Rect
import java.awt.Color

object GameData {
    val player = Rect(240, 240, 20, 20, Color.BLUE)

    private var bufferX = 0
    var shiftX = 0
        set(value) {
            if ((value < field && Board.moveRight()) ||
                    (value > field && Board.moveLeft())) {
                if (Math.abs(value - 250) < 750) {
                    if (bufferX == 0) field = value
                    else bufferX -= 10
                } else bufferX += 10
            }
        }
    private var bufferY = 0
    var shiftY = 0
        set(value) {
            if ((value < field && Board.moveUp()) ||
                    (value > field && Board.moveDown())) {
                if (Math.abs(value - 250) < 750) {
                    if (bufferY == 0) field = value
                    else bufferY -= 10
                } else bufferY += 10
            }
        }

    var playing = false

}