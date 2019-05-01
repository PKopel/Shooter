package main.display

import main.back.Game
import main.back.Game.objects
import main.back.Game.player
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.JTextArea

class MapView : JPanel() {

    val message = JTextArea()

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        for (obstacle in objects.obstacles) obstacle.paint(g)
        for (shooter in objects.shooters) shooter.paint(g)
        for (missile in Game.missiles) missile.paint(g)
        player.paint(g)
    }

    init {
        message.isEditable=false
        message.minimumSize= Dimension(100,30)
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
