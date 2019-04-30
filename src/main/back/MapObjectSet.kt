package main.back

import main.back.GameData.player
import main.data.StringData
import main.data.StyleData
import main.data.ViewData
import kotlin.random.Random

class MapObjectSet(private val maxObst: Int = 200, private val maxSht: Int = 20) {
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

    fun remove(shooter: Shooter): Boolean {
        var found = false
        for (i in 0 until shooters.size) {
            if (shooters[i] === shooter)
                found = true
            if (found) shooters[i] = try {
                shooters[i + 1]
            } catch (e: IndexOutOfBoundsException) {
                Shooter(0, 0, 0, 0)
            }
        }
        if (sizeS == 0) {
            ViewData.view.play.text = StringData.won
            GameData.playing = false
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
        while (GameData.objects.sizeO < 200) {
            GameData.objects.add(Obstacle(
                    (rand.nextInt() % 150 - 50) * 10 + 1,
                    (rand.nextInt() % 150 - 50) * 10 + 1,
                    (6 + (rand.nextInt()) % 6) * 10 - 2,
                    (6 + (rand.nextInt()) % 6) * 10 - 2))
        }
        while (GameData.objects.sizeS<20){
            GameData.objects.add(Shooter(
                    (rand.nextInt() % 150 - 50) * 10 + 1,
                    (rand.nextInt() % 150 - 50) * 10 + 1))
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

