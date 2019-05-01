package main.back

import main.back.Game.player
import main.data.StringData
import main.data.StyleData
import main.data.ViewData
import kotlin.math.abs
import kotlin.random.Random

class MapObjectSet(private val maxObst: Int = 100, private val maxSht: Int = 15) {
    var sizeO = 0
    var sizeS = 0
    val obstacles = Array(maxObst) { Obstacle(0, 0, 0, 0) }
    val shooters = Array(maxSht) { Shooter(0, 0, 0, 0) }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Obstacle): Boolean {
        if (contains(element)) return false
        if (player.equals(element)) return false
        return if (sizeO < maxObst) {
            obstacles[sizeO++] = element
            true
        } else false
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Shooter): Boolean {
        if (contains(element)) return false
        if (player.equals(element)) return false
        return if (sizeS < maxSht) {
            shooters[sizeS++] = element
            true
        } else false
    }

    fun clear() {
        sizeO = 0
        sizeS = 0
    }

    private fun remove(shooter: Shooter): Boolean {
        var found = false
        for (i in 0 until sizeS) {
            if (shooters[i] === shooter) {
                found = true
                sizeS--
            }
            if (found) shooters[i] = try {
                shooters[i + 1]
            } catch (e: IndexOutOfBoundsException) {
                Shooter(0, 0, 0, 0)
            }
        }
        if (sizeS == 0) {
            ViewData.view.play.text = StringData.won
            Game.playing = false
        }
        return found
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun contains(missile: Missile): Boolean {
        for (obst in obstacles) if (missile.equals(obst)) return true
        for (sht in shooters) if (missile.equals(sht)) {
            if (missile.color == StyleData.pMissile) remove(sht)
            return true
        }
        return false
    }

    fun contains(element: MapObject): Boolean {
        for (obst in obstacles) if (obst == element) return true
        for (sht in shooters) if (sht == element) return true
        return false
    }

    fun fillMap() {
        val rand = Random(System.currentTimeMillis())
        while (Game.objects.sizeO < maxObst) {
            Game.objects.add(Obstacle(
                    (abs(rand.nextInt())% 150 - 50) * 10 + 1,
                    (abs(rand.nextInt())% 150 - 50) * 10 + 1,
                    (6 + (rand.nextInt()) % 6) * 10 - 2,
                    (6 + (rand.nextInt()) % 6) * 10 - 2))
        }
        while (Game.objects.sizeS < maxSht){
            Game.objects.add(Shooter(
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1,
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1))
        }
    }
/*
    inner class ObstacleIterator: Iterator<Obstacle>{
        private var i = 0
        override fun next(): Obstacle {
            return if(hasNext()){
                objects[i++]
            } else Obstacle(0,0,0,0)
        }

        override fun hasNext() = i<sizeO
    }

    override fun iterator(): Iterator<MapObject> = ObstacleIterator()

 */
}

