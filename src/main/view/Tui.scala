package main.view

import main.controller.Controller
import main.model.MinesMapHelper
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {
    val input_without_spaces = input.replaceAll("\\s", "")
    val input_coordinates = input_without_spaces(1).toString + input_without_spaces(2).toString

    val mines_map_helper = new MinesMapHelper
    val row_col_index = mines_map_helper.getRolAndColIndex(input_coordinates)

    if(row_col_index._1 == -1){
      println("Falsche Eingabe")
    }else{
      input_without_spaces.head match {
        case 'q' => println("Bis bald!")
        case 'o'=> controller.openField(row_col_index)
        case 'f' => controller.placeFlag(row_col_index)
        case 'n' => controller.placeNote(row_col_index)
        case 's' => controller.solve
        case _ => println("Falsche Eingabe")
      }
    }

  }
  override def update: Boolean = { println(controller.gameMapToString);true}

}
