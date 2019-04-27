package main.data

import main.back.Board
import java.awt.Color

object StyleData {

    var theme: Color = Color.LIGHT_GRAY
    var background: Color = Color.WHITE
    var obstacles: Color = Color.ORANGE
        set(value) {
            for( o in Board.obstacles) if(o!==GameData.player) o.color=value
            field=value
        }

    var shooters: Color = Color.RED
        /*set(value) {
            field = value
            Board.reset()
        }

         */
}
