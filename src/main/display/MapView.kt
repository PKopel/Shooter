package main.display

import main.back.Board
import main.data.GameData.player
import main.data.StyleData.shooters
import main.data.GameData.shiftX
import main.data.GameData.shiftY
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class MapView : JPanel() {

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        for ((x, y, widthR, heightR, color) in Board.obstacles) {
            g.color = color
            when (color) {
                player.color -> g.fillOval(x + shiftX, y + shiftY, widthR, heightR)
                shooters -> g.fillOval(x + shiftX, y + shiftY, widthR, heightR)
                else -> g.fillRect(x + shiftX, y + shiftY, widthR, heightR)
            }
        }
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