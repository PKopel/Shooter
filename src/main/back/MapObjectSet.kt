package main.back

import main.data.GameData.player

class MapObjectSet(private val maxObst: Int=200, private val maxSht: Int=20){
    var sizeO = 0
    var sizeS = 0
    val obstacles = Array(maxObst){ Obstacle(0,0,0,0) }
    val shooters = Array(maxSht){ Shooter(0,0,0,0) }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Obstacle): Boolean{
        if(contains(element)) return false
        if(player.equals(element)) return false
        return if(sizeO<maxObst){
            obstacles[sizeO++]=element
            true
        } else false
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    fun add(element: Shooter): Boolean{
        if(contains(element)) return false
        if(player.equals(element)) return false
        return if(sizeS<maxSht){
            shooters[sizeS++]=element
            true
        } else false
    }
    /*
    fun remove(element: MapObject): Boolean{
        var found = false
        for(i in 0 until objects.size){
            if(objects[i]==element)
                found = true
            if(found) objects[i]=objects[i+1]
        }
        return found
    }
*/
    fun contains(element: MapObject): Boolean{
        for(obst in obstacles) if(obst == element) return true
        for(sht in shooters) if(sht == element) return true
        return false
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
