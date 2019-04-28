package main.back

import java.lang.Math.abs
import java.util.concurrent.LinkedBlockingQueue

class Missiles: LinkedBlockingQueue<Missile>(){
    fun clean(){
        for(missile in this)
            if(abs(missile.x-250)>1000 || abs(missile.y-250)>1000)
                remove(missile)
    }
}