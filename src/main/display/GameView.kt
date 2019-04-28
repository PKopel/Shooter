package main.display

import main.App
import main.button
import main.data.GameData
import main.data.GameData.player
import main.data.GameData.playing
import main.data.GameData.shiftX
import main.data.GameData.shiftY
import main.data.StringData
import main.data.StyleData
import main.data.StyleData.theme
import main.data.ViewData.game
import main.fillMap
import main.run
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class GameView : JFrame() {
    private val buttons = JPanel()
    private val back = button(StringData.ret, theme) {
        playing = false
        run(App, 150, 150, StringData.appName)
        dispose()
    }
    val play = button(StringData.play, theme) {
        val play = (it.source as JButton)
        when (play.text) {
            StringData.lost,StringData.won -> {
                GameData.reset()
                fillMap()
                play.text = StringData.play
            }
            else -> playing = !playing
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
                    0xa -> {
                        player.shoot()
                    }
                    0x10 -> player.direction=player.direction.next()
                    else -> println(k.paramString())
                }
        }
    }

    init {
        fillMap()
        back.addKeyListener(kl)
        play.addKeyListener(kl)
        add(game)
        buttons.background = StyleData.background
        buttons.layout = FlowLayout()
        buttons.add(play)
        buttons.add(back)
        add(BorderLayout.SOUTH, buttons)
    }
}
