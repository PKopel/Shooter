package main.display

import main.App
import main.button
import main.data.GameData.game
import main.data.GameData.player
import main.data.GameData.playing
import main.data.GameData.shiftX
import main.data.GameData.shiftY
import main.data.StyleData
import main.fillMap
import main.run
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class GameView(app: App) : JFrame() {
    private val data = StyleData
    private val buttons = JPanel()
    private val back = button("Return", data.theme) {
        run(app, 300, 100, "Shooter")
        dispose()
    }
    private val play = button("Play", data.theme) {
        when (playing) {
            true -> {
                playing = false
                (it.source as JButton).text = "Play"
            }
            false -> {
                playing = true
                (it.source as JButton).text = "Stop"
            }
        }
    }
    private val kl = object : KeyAdapter() {
        override fun keyPressed(k: KeyEvent) {
            if (playing)
                when (k.extendedKeyCode) {
                    0x44, 0x27 -> {
                        shiftX -= 10; game.repaint()
                    }
                    0x57, 0x26 -> {
                        shiftY += 10; game.repaint()
                    }
                    0x53, 0x28 -> {
                        shiftY -= 10; game.repaint()
                    }
                    0x41, 0x25 -> {
                        shiftX += 10; game.repaint()
                    }
                    0x20 -> { player.shoot() }
                    else -> println(k.paramString())
                }
        }
    }

    init {
        fillMap()
        back.addKeyListener(kl)
        play.addKeyListener(kl)
        add(game)
        buttons.background = data.background
        buttons.layout = FlowLayout()
        buttons.add(play)
        buttons.add(back)
        add(BorderLayout.SOUTH, buttons)
    }
}
