package test

import java.awt.Color
import java.awt.Graphics
import javax.swing.JFrame
import main.run
import javax.swing.JPanel

abstract class Painter{
    abstract fun paint(g: Graphics)
}

class Circle : Painter(){
    override fun paint(g: Graphics) {
        g.color= Color.YELLOW
        g.fillOval(20,20,20,20)
    }
}

class Square : Painter(){
    override fun paint(g: Graphics) {
        g.color= Color.BLUE
        g.fillRect(40,40,20,20)
    }
}

class View : JPanel(){
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        Circle().paint(g)
        //g.color= Color.YELLOW
        //g.fillOval(20,20,20,20)
        Square().paint(g)
        //g.color= Color.BLUE
        //g.fillRect(40,40,20,20)
    }

}

object Painters : JFrame() {
    private val view = View()
    init{
        add(view)
        view.repaint()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        run(this,300,300)
    }
}