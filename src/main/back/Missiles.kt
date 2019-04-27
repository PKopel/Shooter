package main.back

import java.lang.Math.abs
import java.util.*

class Missiles: LinkedList<Missile>(){
    fun clean(){
        for(missile in this)
            if(abs(missile.x-250)>750 || abs(missile.y-250)>750)
                remove(missile)
    }
}