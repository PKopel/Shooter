package main.display

import java.awt.Color
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JRadioButtonMenuItem

fun getRGBColor(r: Int, g: Int, b:Int): Color{
    val hsb = FloatArray(3)
    Color.RGBtoHSB(r,g,b,hsb)
    return Color.getHSBColor(hsb[0],hsb[1],hsb[2])
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