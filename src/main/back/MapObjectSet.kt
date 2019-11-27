package main.back

import main.back.Game.level
import main.back.objects.*
import main.data.StyleData
import kotlin.math.abs
import kotlin.random.Random

class MapObjectSet {
    var player = Player()
    private var sizeO = 0
    private var sizeS = 0
    val obstacles: Array<Obstacle?> = Array(level.obsNum) { null }
    val shooters: Array<Shooter?> = Array(level.shtNum) { null }
    private val bounds = arrayOf(Obstacle(-500, -500, 0, 1500),
            Obstacle(1000, -500, 0, 1500),
            Obstacle(-500, -500, 1500, 0),
            Obstacle(-500, 1000, 1500, 0))

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Obstacle): Boolean {
        if (contains(element)) return false
        if (player.equals(element)) return false
        return if (sizeO < level.obsNum) {
            obstacles[sizeO++] = element
            true
        } else false
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Shooter): Boolean {
        if (contains(element)) return false
        if (player.equals(element)) return false
        return if (sizeS < level.shtNum) {
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
                null
            }
        }
        if (sizeS == 0) {
            Game.won()
        }
        return found
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun contains(missile: Missile): Boolean {
        for (bound in bounds) if (missile.equals(bound)) return true
        for (obst in obstacles) if (missile.equals(obst)) return true
        for (sht in shooters)
            if (missile.equals(sht))
                if (missile.color == StyleData.pMissile) return remove(sht)
        if(missile.equals(player) && missile.color!=StyleData.pMissile) return player.hit()
        return false
    }

    fun contains(element: MapObject): Boolean {
        for (bound in bounds) if (element == bound) return true
        for (obs in obstacles) if (obs == element) return true
        for (sht in shooters) if (sht == element) return true
        return false
    }

    fun fillMap() {
        player = Player(color = StyleData.player)
        val rand = Random(System.currentTimeMillis())
        while (Game.objects.sizeO < level.obsNum) {
            Game.objects.add(Obstacle(
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1,
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1,
                    (6 + (rand.nextInt()) % 6) * 10 - 2,
                    (6 + (rand.nextInt()) % 6) * 10 - 2))
        }
        while (Game.objects.sizeS < level.shtNum) {
            Game.objects.add(Shooter(
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1,
                    (abs(rand.nextInt()) % 150 - 50) * 10 + 1, shoot = level::shoot))
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

