package main.back

import java.awt.GraphicsConfiguration
import java.awt.GraphicsEnvironment
import java.awt.Transparency
import java.awt.image.BufferedImage

fun angle(x1: Int, x2:Int, y1:Int, y2:Int) : Double =
        Math.atan2((y2-y1).toDouble(),(x1-x2).toDouble())

fun rotate(image: BufferedImage, angle: Double): BufferedImage {
    fun getDefaultConfiguration(): GraphicsConfiguration {
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val gd = ge.defaultScreenDevice
        return gd.defaultConfiguration
    }
    val sin = Math.abs(Math.sin(angle))
    val cos = Math.abs(Math.cos(angle))
    val w = image.width
    val h = image.height
    val neww = Math.floor(h * cos + w * sin).toInt()
    val newh = Math.floor(w * cos + h * sin).toInt()
    val gc = getDefaultConfiguration()
    val result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT)
    val g = result.createGraphics()
    g.translate((neww - w) / 2, (newh - h) / 2)
    g.rotate(angle, (w / 2).toDouble(), (h / 2).toDouble())
    g.drawRenderedImage(image, null)
    g.dispose()
    return result
}

infix fun <T : Comparable<T>> ClosedRange<T>.intersection(r: ClosedRange<T>): ClosedRange<T>? {
    val l = when {
        this contains r.start -> r.start
        r contains this.start -> this.start
        else -> null
    }
    val h = when {
        this contains r.endInclusive -> r.endInclusive
        r contains this.endInclusive -> this.endInclusive
        else -> null
    }
    return if (l != null && h != null) l..h
    else null
}

infix operator fun <T : Comparable<T>> ClosedRange<T>.contains(n: T): Boolean {
    return this.start <= n && n <= this.endInclusive
}