package main.display

import main.App
import main.back.Board
import main.back.Rect
import main.button
import main.run
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame

class GameView(app: App) : JFrame() {
    private val back = button("Return", app.background) {
        run(app, 300, 100, "Shooter")
        dispose()
    }
    /*
    private val play = button("Play", Color.GREEN) {
        if(playing) Board.obstacles.add(Rect(
                20,20,20,20, Color.CYAN
        ))
        else Board.obstacles.add(Rect(
                25,25,20,20, Color.GRAY
        ))
        playing=!playing
    }
     */
    private val game = Game()
    //private var playing = false
    private val kl = object : KeyAdapter() {
        override fun keyPressed(k: KeyEvent) {
            when (k.extendedKeyCode) {
                0x44, 0x27 -> { game.shiftX-=10; game.repaint() }
                0x57, 0x26 -> { game.shiftY+=10; game.repaint() }
                0x53, 0x28 -> { game.shiftY-=10; game.repaint() }
                0x41, 0x25 -> { game.shiftX+=10; game.repaint() }
                else -> println(k.paramString())
            }
        }
    }

    init {
        Board.fillMap()
        back.addKeyListener(kl)
        //play.addKeyListener(kl)
        add(game)
        //add(BorderLayout.NORTH, play)
        add(BorderLayout.SOUTH, back)
    }
}