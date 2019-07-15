package test

object AngleTest {
    fun angle(x1: Int, x2:Int, y1:Int, y2:Int) : Double =
            Math.atan2((y1-y2).toDouble(),(x1-x2).toDouble())/Math.PI

    @JvmStatic
    fun main(args: Array<String>) {
        for(i in -1..1){
            for(j in -1..1) {
                println("$i,  $j, ${angle(i,0,j ,0)}")
            }
        }
    }
}