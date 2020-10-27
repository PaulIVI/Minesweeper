import main.controller.Controller
import main.model.{GameLogic, GameMap}
import main.view.Tui

import scala.io.StdIn.readLine

object Minesweeper {
  def main(args: Array[String]) = {
    val controller = new Controller(new GameMap(10), new GameLogic)
    val tui = new Tui(controller)
    controller.notifyObservers

    def main(args: Array[String]): Unit = {
      var input: String = "r"
      if (!input.isEmpty) tui.processInputLine(input)
      else do {
        input = readLine()
        tui.processInputLine(input)
      } while (input != "q")
    }

//    val difficulty = 10
//
//    val game_map = Vector(Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0))
//    val current_game_map = Vector(Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1))
//
//    val user_interface = new main.model.UI
//    val console_string = user_interface.gameMapToString(game_map, current_game_map)
//    println(console_string)
//
//    val mine = new main.model.GameMap
//    val mapMine = mine.createMap(difficulty)

//    val logic = new GameLogic
//    val game_situation = logic.startTheGame(difficulty)
//
//    printf(mapMine.toString())
//    printf(game_situation.toString())
  }
}