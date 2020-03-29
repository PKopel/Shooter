package main.display

import main.back.Game
import main.back.Game.objects
import main.back.Game.scale
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.JTextArea

class MapView : JPanel() {

    val message = JTextArea()

    override fun paintComponent(g: Graphics) {
        if (this.height < this.width) {
            scale = this.height.toFloat() / 700
            this.setSize((700 * scale).toInt(), this.height)
        } else {
            scale = this.width.toFloat() / 700
            this.setSize(this.width, (700 * scale).toInt())
        }
        super.paintComponent(g)
        for (obstacle in objects.obstacles) obstacle?.paint(g)
        for (shooter in objects.shooters) shooter?.paint(g)
        for (missile in Game.missiles) missile.paint(g)
        objects.player.paint(g)
    }

    init {
        message.isEditable = false
        message.font = message.font.deriveFont(30f)
        message.minimumSize = Dimension(100, 30)
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
