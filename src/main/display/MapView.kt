package main.display

import main.data.GameData
import main.data.GameData.objects
import main.data.GameData.player
import main.data.GameData.shiftX
import main.data.GameData.shiftY
import main.data.StyleData
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class MapView : JPanel() {

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = StyleData.obstacles
        for ((x, y, w, h) in objects.obstacles)
            g.fillRect(x + shiftX, y + shiftY, w, h)
        g.color = StyleData.shooters
        for (shooter in objects.shooters) {
            g.fillPolygon(shooter.xCoords(), shooter.yCoords(), 8)
        }
        for ((x, y, _, color) in GameData.missiles) {
            g.color = color
            g.fillOval(x + shiftX, y + shiftY, 2, 2)
        }
        g.color = StyleData.player
        val (x, y, w, h) = player
        g.fillOval(x + shiftX, y + shiftY, w, h)
    }

    init {
        background = Color.WHITE
        repaint()
    }
}

/*
g.color=Color.GRAY
        g.drawLine(-500,-500,-500,1000)
        g.drawLine(-500,-500,1000,-500)
        g.drawLine(-500,1000,1000,1000)
        g.drawLine(1000,-500,1000,1000)
 */
