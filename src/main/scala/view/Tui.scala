package main.view

import main.controller.Controller
import main.model.MinesMapHelper
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {
    val input_without_spaces = input.replaceAll("\\s", "").toLowerCase()
    val col_and_row_index = getRowAndColIndex(input_without_spaces.substring(1))
    println(col_and_row_index)

    val wrong_input_string = "Falsche Eingabe. OXY=Open, FXY=Flag, NXY=Note, S=Solve, Q=Quitt"

    input_without_spaces.head match {
      case 'q' => println("Bis bald!")
      case 'o'=> if(col_and_row_index._1 != -1) controller.openField(col_and_row_index) else println(wrong_input_string)
      case 'f' => if(col_and_row_index._1 != -1) controller.placeFlag(col_and_row_index) else println(wrong_input_string)
      case 'n' => if(col_and_row_index._1 != -1) controller.placeNote(col_and_row_index) else println(wrong_input_string)
      case 's' => controller.solve
      case _ => println(wrong_input_string)
    }
  }

  def getRowAndColIndex(input:String): (Int,Int) ={
    val mines_map_helper = new MinesMapHelper
    if(input.length>1) mines_map_helper.getRolAndColIndex(input)
    else (-1,-1)
  }

  override def update: Boolean = { println(controller.gameMapToString);true}

}
