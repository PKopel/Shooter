package main.display

import main.App
import main.button
import main.back.GameData
import main.back.GameData.damage
import main.back.GameData.player
import main.back.GameData.playing
import main.back.GameData.shiftX
import main.back.GameData.shiftY
import main.data.StringData
import main.data.StyleData
import main.data.StyleData.theme
import main.data.ViewData.game
import main.run
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class GameView : JFrame() {
    private val buttons = JPanel()
    private val back = button(StringData.ret, theme) {
        if(damage>=10) GameData.reset()
        else playing = false
        run(App, 150, 150, StringData.appName)
        dispose()
    }
    val play = button(StringData.play, theme) {
        val play = (it.source as JButton)
        when (play.text) {
            StringData.lost,StringData.won -> {
                GameData.reset()
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
                    0x10 -> player.direction=player.direction.next()
                    0xa -> player.shoot()
                    else -> println(k.paramString())
                }
        }
    }

    private val ml = object : MouseAdapter(){

        override fun mouseClicked(p0: MouseEvent?) {
            if(playing) player.shoot()
        }

    }

    init {
        back.addKeyListener(kl)
        play.addKeyListener(kl)
        game.addMouseListener(ml)
        add(game)
        buttons.background = StyleData.background
        buttons.layout = FlowLayout()
        buttons.add(play)
        buttons.add(back)
        add(BorderLayout.SOUTH, buttons)
    }
}
