package main.back

import main.back.objects.Obstacle

object Move {

    var x = Game.objects.player.x
        set(value) {
            Game.objects.player.x = value
            field = value
        }
    var y = Game.objects.player.y
        set(value) {
            Game.objects.player.y = value
            field = value
        }


    private var bufferX = 0
    var shiftX = 0
        set(value) {
            if ((value < field && moveRight()) ||
                    (value > field && moveLeft())) {
                if (Math.abs(value - 100) < 400) {
                    if (bufferX == 0) field = value
                    else bufferX -= 10
                } else bufferX += 10
            }
        }
    private var bufferY = 0
    var shiftY = 0
        set(value) {
            if ((value < field && moveUp()) ||
                    (value > field && moveDown())) {
                if (Math.abs(value - 65) < 435) {
                    if (bufferY == 0) field = value
                    else bufferY -= 10
                } else bufferY += 10
            }
        }

    fun moveLeft(): Boolean {
        return if (Game.objects.contains(Obstacle(x - 10, y, 20, 20))) false
        else {
            x -= 10
            true
        }
    }

    fun moveRight(): Boolean {
        return if (Game.objects.contains(Obstacle(x + 10, y, 20, 20))) false
        else {
            x += 10
            true
        }
    }

    fun moveUp(): Boolean {
        return if (Game.objects.contains(Obstacle(x, y + 10, 20, 20))) false
        else {
            y += 10
            true
        }
    }

    fun moveDown(): Boolean {
        return if (Game.objects.contains(Obstacle(x, y - 10, 20, 20))) false
        else {
            y -= 10
            true
        }
    }

    fun reset() {
        shiftX = 0
        shiftY = 0
        bufferX = 0
        bufferY = 0
    }
}