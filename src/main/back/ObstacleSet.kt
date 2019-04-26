package main.back

import java.awt.Color

class ObstacleSet(private val maxSize: Int=200) : AbstractSet<Rect>(){
    override var size = 0
    private val zero = Rect(0,0,0,0)
    val player = Rect(240,240, 20,20, Color.BLUE)
    private val elements = Array(maxSize){ zero }
    /*private val bounds = arrayOf(
            Rect(-500,-510,1500,10),
            Rect(1000,-500,10,1500),
            Rect(-500,1000,1500,10),
            Rect(-510,-500,10,1500))


     */
    fun add(element: Rect): Boolean{
        if(contains(element)) return false
        return if(size<maxSize){
            elements[size]=element
            size++; true
        } else false
    }

    fun remove(element: Rect): Boolean{
        var found = false
        for(i in 0 until elements.size){
            if(elements[i]==element)
                found = true
            if(found) elements[i]=elements[i+1]
        }
        return found
    }

    override fun contains(element: Rect): Boolean{
        for(rect in elements) if(rect == element) return true
        //for(bound in bounds) if(bound == element) return true
        return false
    }

    public fun full() = size==maxSize

    inner class ObstacleIterator: Iterator<Rect>{
        private var i = -1
        override fun next(): Rect {
            return if(hasNext()){
                when(i++){
                    0 -> player
                    else -> elements[i]
                }
            } else zero
        }

        override fun hasNext() = i<size
    }

    override fun iterator(): Iterator<Rect> = ObstacleIterator()
    /*companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val r1=Rect(1,1,10,10)
            val r2=Rect(2,2,10,10)
            val r3=Rect(100,100,1,1)
            val os=ObstacleSet(3)
            println(os.size)
            os.add(r1)
            println(os.size)
            os.add(r2)
            println(os.size)
            os.add(r3)
            println(os.size)
        }
    }*/
}
