package main

import main.back.Obstacle
import main.back.Shooter
import main.data.GameData
import java.awt.Color
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JRadioButtonMenuItem
import javax.swing.SwingUtilities.invokeLater
import kotlin.random.Random

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

fun fillMap() {
    val rand = Random(System.currentTimeMillis())
    while (GameData.objects.sizeO < 200) {
        GameData.objects.add(Obstacle(
                (rand.nextInt() % 150 - 50) * 10 + 1,
                (rand.nextInt() % 150 - 50) * 10 + 1,
                (6 + (rand.nextInt()) % 6) * 10 - 2,
                (6 + (rand.nextInt()) % 6) * 10 - 2))
    }
    while (GameData.objects.sizeS<20){
        GameData.objects.add(Shooter(
                (rand.nextInt() % 150 - 50) * 10 + 1,
                (rand.nextInt() % 150 - 50) * 10 + 1))
    }
}

fun radioMenuItem(text: String = "", background: Color = Color.LIGHT_GRAY,
                  listener: (ActionEvent) -> Unit, selected: Boolean = false): JRadioButtonMenuItem {
    val item = JRadioButtonMenuItem(text)
    item.isSelected = selected
    item.background = background
    item.addActionListener(listener)
    return item
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
    val h = when {
        this contains r.endInclusive -> r.endInclusive
        r contains this.endInclusive -> this.endInclusive
        else -> null
    }
    return if (l != null && h != null) l..h
    else null
}

infix operator fun <T : Comparable<T>> ClosedRange<T>.contains(n: T): Boolean {
    return this.start <= n && n <= this.endInclusive
}
