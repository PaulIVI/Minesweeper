package main.view

import main.controller.Controller
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "o"=> controller.openField(input)
      case "m" => controller.placeNote(input)
      case "f" => controller.solve
    }
  }
  override def update: Boolean = { println(controller.gameMapToString);true}

}
