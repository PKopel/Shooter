package main

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

