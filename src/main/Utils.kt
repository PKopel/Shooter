package main

import java.awt.Color
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingUtilities.invokeLater

fun run(f: JFrame, width: Int, height: Int) {
    invokeLater {
        f.title = f::class.java.simpleName
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.setSize(width, height)
        f.isVisible = true
    }
}

fun run(f: JFrame, width: Int, height: Int, name: String) {
    invokeLater {
        f.title = name
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.setSize(width, height)
        f.isVisible = true
    }
}

fun button(text: String = "", background: Color = Color.LIGHT_GRAY, listener: (ActionEvent) -> Unit): JButton {
    val button = JButton(text)
    button.background = background
    button.addActionListener(listener)
    return button
}

infix fun <T : Comparable<T>> ClosedRange<T>.intersection(r: ClosedRange<T>): ClosedRange<T>? {
    val l = when {
        this contains r.start -> r.start
        r contains this.start -> this.start
        else -> null
    }
    val h = when{
        this contains r.endInclusive -> r.endInclusive
        r contains this.endInclusive -> this.endInclusive
        else -> null
    }
    return if(l!=null && h!= null) l..h
    else null
}

infix operator fun <T : Comparable<T>> ClosedRange<T>.contains(n: T): Boolean {
    return this.start <= n && n <= this.endInclusive
}