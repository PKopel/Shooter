package test

import java.awt.GraphicsEnvironment
import java.awt.GraphicsConfiguration
import java.awt.Transparency
import java.awt.image.BufferedImage



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
        val neww = Math.floor(w * cos + h * sin).toInt()
        val newh = Math.floor(h * cos + w * sin).toInt()
        val gc = getDefaultConfiguration()
        val result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT)
        val g = result.createGraphics()
        g.translate((neww - w) / 2, (newh - h) / 2)
        g.rotate(angle, (w / 2).toDouble(), (h / 2).toDouble())
        g.drawRenderedImage(image, null)
        g.dispose()
        return result
    }
