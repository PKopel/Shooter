package main.display

import main.back.Board
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class Game : JPanel() {
    var bufferX=0
    var shiftX = 0
        set(value) {

            if ((value < field && Board.moveRight()) ||
                    (value > field && Board.moveLeft())) {
                if (Math.abs(value - 250) < 750) {
                    if(bufferX==0) field = value
                    else bufferX-=10
                }
                else bufferX+=10
            }
        }
    var bufferY=0
    var shiftY = 0
        set(value) {
            //val (x,y,width,height)=player
            //val tmp = Player(x+value,y+value,width, height)

            if ((value < field && Board.moveUp()) ||
                    (value > field && Board.moveDown())) {
                if (Math.abs(value - 250) < 750) {
                    if(bufferY==0) field = value
                    else bufferY-=10
                }
                else bufferY+=10
            }
        }


    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        println("painting")
        //for (i in Board.map.indices) {
        for ((x, y, widthR, heightR, color) in Board.obstacles) {
            //val (x, y, widthR, heightR, color) = Board.map[i]
            g.color = color
            when (color) {
                Board.obstacles.player.color -> g.fillOval(x + shiftX, y + shiftY, widthR, heightR)
                else -> g.fillRect(x + shiftX, y + shiftY, widthR, heightR)
            }
        }
    }

    init {
        Board.obstacles.player.x=300
        Board.obstacles.player.y=300
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