package main.back

import main.data.GameData.player
import kotlin.random.Random

object Board {
    val obstacles = ObstacleSet()

    fun moveLeft(): Boolean {
        val (x, y, w, h) = player
        if (obstacles.contains(Rect(x - 10, y, w, h))) return false
        else player.moveLeft(); return true
    }

    fun moveRight(): Boolean {
        val (x, y, w, h) = player
        if (obstacles.contains(Rect(x + 10, y, w, h))) return false
        else player.moveRight(); return true
    }

    fun moveUp(): Boolean {
        val (x, y, w, h) = player
        if (obstacles.contains(Rect(x, y + 10, w, h))) return false
        else player.moveUp(); return true
    }

    fun moveDown(): Boolean {
        val (x, y, w, h) = player
        if (obstacles.contains(Rect(x, y - 10, w, h))) return false
        else player.moveDown(); return true
    }

    fun fillMap() {
        val rand = Random(System.currentTimeMillis())
        while (obstacles.size < 199) {
            obstacles.add(Rect(
                    (rand.nextInt() % 150 - 50) * 10 + 1,
                    (rand.nextInt() % 150 - 50) * 10 + 1,
                    (6 + (rand.nextInt()) % 6) * 10 - 2,
                    (6 + (rand.nextInt()) % 6) * 10 - 2))
        }
    }
}