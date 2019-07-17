package main.back.level

abstract class Level {

    public abstract val obsNum: Int
    public abstract val shtNum: Int
    public abstract val hpPool: Double

    abstract fun shoot(x: Int, y: Int, dir: Double)

}