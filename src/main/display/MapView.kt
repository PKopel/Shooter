package main.display

import main.back.Game
import main.back.Game.objects
import main.back.Game.player
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class MapView : JPanel() {

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        for (obstacle in objects.obstacles) obstacle.paint(g)
        for (shooter in objects.shooters) shooter.paint(g)
        for (missile in Game.missiles) missile.paint(g)
        player.paint(g)
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
