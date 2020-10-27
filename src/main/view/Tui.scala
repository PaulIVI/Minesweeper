package main.view

import main.controller.Controller

class Tui(controller: Controller) {
  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "o"=> controller.openField(input)
      case "m" => controller.placeNote(input)
      case "f" => controller.solve
        val success= controller.solve
        if (success) println("Puzzle solved")else println("This puzzle could not be solved!")
      case _ => input.toList.filter(c => c != ' ').filter(_.isDigit).map(c => c.toString.toInt) match {
        case row :: column :: value :: Nil => controller.set(row, column, value)
        case _ => println("Wrong input!")
      }

    }
  }

}
