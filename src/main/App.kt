package main

import main.back.Game
import main.back.MapObjectSet
import main.data.StringData
import main.data.StringData.level
import main.data.StyleData
import main.data.ViewData
import main.display.button
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Graphics
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JPanel

class App : JFrame() {
    private val buttons = JPanel()
    private val image = object: JPanel(){
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            g.drawImage(ImageIO.read(File("graphics/starting.png")),0,0,this)
        }
    }
    private val data = StyleData
    private val difficulty = JComboBox<String>(level)
    private val start = button(StringData.play, data.theme) {
        run(ViewData.view, data.gameWidth, data.gameHeight, StringData.appName)
        dispose()
    }
    private val settings = button(StringData.settings, data.theme) {
        run(ViewData.settings, data.settingsWidth, data.settingsHeight)
        dispose()
    }
    private val exit = button(StringData.exit, data.theme) {
        dispose()
    }

    init {
        difficulty.addActionListener {
            when ((it.source as JComboBox<String>).selectedItem) {
                level[0] -> Game.objects = MapObjectSet(maxSht = 15)
                level[1] -> Game.objects = MapObjectSet(maxSht = 20)
                level[2] -> Game.objects = MapObjectSet(75, 25)
                level[3] -> Game.objects = MapObjectSet(50, 30)
            }
            Game.reset()
        }
        difficulty.background = data.theme
        background = data.background
        add(image)
        buttons.layout=FlowLayout()
        buttons.add(difficulty)
        buttons.add(start)
        buttons.add(settings)
        buttons.add(exit)
        add(BorderLayout.SOUTH, buttons)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(App(), StyleData.appWidth, StyleData.appHeight, StringData.appName)
        }
    }
}
