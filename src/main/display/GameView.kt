package main.display

import main.back.Game
import main.back.Game.damage
import main.back.Game.playing
import main.back.Move.shiftX
import main.back.Move.shiftY
import main.data.StringData
import main.data.StyleData
import main.data.StyleData.theme
import main.data.ViewData.game
import main.run
import java.awt.BorderLayout
import java.awt.Component
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class GameView : JFrame() {
    private val buttons = JPanel()
    private val back = button(StringData.ret, theme) {
        if (damage >= 10) Game.reset()
        else playing = false
        run(App(), 150, 200, StringData.appName)
        dispose()
    }
    val play = button(StringData.play, theme) {
        val play = (it.source as JButton)
        when (play.text) {
            StringData.lost, StringData.won -> {
                Game.reset()
                play.text = StringData.play
            }
            else -> playing = !playing
        }
    }
    private val reset = button(StringData.reset, theme) {
        Game.reset()
        play.text = StringData.play
        play.isVisible = true
        game.remove(game.message)
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
                    else -> println(k.paramString())
                }
        }
    }

    private val ml = object : MouseAdapter() {

        override fun mouseClicked(e: MouseEvent) {
            //e.translatePoint(shiftX, shiftY)
            if (playing) Game.objects.player.shoot(e.x, e.y)
        }
    }

    init {
        back.addKeyListener(kl)
        play.addKeyListener(kl)
        game.addMouseListener(ml)
        this.add(game.apply { alignmentX = Component.CENTER_ALIGNMENT; })
        buttons.background = StyleData.background
        buttons.layout = FlowLayout()
        buttons.add(play)
        buttons.add(reset)
        buttons.add(back)
        this.add(BorderLayout.SOUTH, buttons)
    }
}
