import main.controller.Controller
import main.model.MinesMap
import main.model.MinesMap
import main.view.Tui

import scala.io.StdIn.readLine

object Minesweeper {
  val controller = new Controller(new MinesMap(10))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (!input.isEmpty) then tui.processInputLine(input)
    else
      while
        input != "q"
      do
        input = readLine()
        tui.processInputLine(input)
  }
}
