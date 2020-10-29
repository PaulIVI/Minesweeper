package main.view

import main.controller.Controller
import main.model.MinesMapHelper
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {
    val input_without_spaces = input.replaceAll("\\s", "")
    val col_and_row_index = getRowAndColIndex(input_without_spaces.substring(1))
    println(col_and_row_index)

    input_without_spaces.head match {
      case 'q' => println("Bis bald!")
      case 'o'=> if(col_and_row_index._1 != -1) controller.openField(col_and_row_index)
      case 'f' => if(col_and_row_index._1 != -1) controller.placeFlag(col_and_row_index)
      case 'n' => if(col_and_row_index._1 != -1) controller.placeNote(col_and_row_index)
      case 's' => controller.solve
      case _ => println("Falsche Eingabe")
    }
  }

  def getRowAndColIndex(input:String): (Int,Int) ={
    val mines_map_helper = new MinesMapHelper
    if(input.length>1) mines_map_helper.getRolAndColIndex(input)
    else (-1,-1)
  }

  override def update: Boolean = { println(controller.gameMapToString);true}

}
