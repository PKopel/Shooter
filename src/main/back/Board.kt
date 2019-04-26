package main.back

import java.awt.Color
import java.util.*

import kotlin.random.Random



object Board {

    /*val map : LinkedList<Rect> = object: LinkedList<Rect>(){
        override fun removeAt(id: Int): Rect {
            obstacles.remove(this[id])
            return super.removeAt(id)
        }
    }*/
    val obstacles = ObstacleSet()
    private val player = obstacles.player
    /*object: HashSet<Rect>(){
        override fun add(element: Rect): Boolean {
            return if(super.add(element)){
                map.add(0,element)
                true
            } else false
        }
    }*/
    //private val player = Player(width / 2 - 10, height / 2 - 10, 20, 20)

    fun moveLeft(): Boolean{
        val (x,y,w,h) = player
        if(obstacles.contains(Rect(x-10,y,w,h))) return false
        else player.moveLeft(); return true
    }

    fun moveRight(): Boolean{
        val (x,y,w,h) = player
        if(obstacles.contains(Rect(x+10,y,w,h))) return false
        else player.moveRight(); return true
    }

    fun moveUp(): Boolean{
        val (x,y,w,h) = player
        if(obstacles.contains(Rect(x,y+10,w,h))) return false
        else player.moveUp(); return true
    }

    fun moveDown(): Boolean{
        val (x,y,w,h) = player
        if(obstacles.contains(Rect(x,y-10,w,h))) return false
        else player.moveDown(); return true
    }

    fun fillMap(){
        val rand = Random(System.currentTimeMillis())
        while(obstacles.size<199){
            obstacles.add(Rect(
                    (rand.nextInt()%150-50)*10+1,
                    (rand.nextInt()%150-50)*10+1,
                    (6+(rand.nextInt())%6)*10-2,
                    (6+(rand.nextInt())%6)*10-2))
        }
    }
}