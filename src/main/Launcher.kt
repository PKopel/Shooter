package main

import main.data.StringData
import main.display.App

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        run(App(), 150, 200, StringData.appName)
    }
}