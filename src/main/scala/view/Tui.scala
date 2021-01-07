package main.view

import main.controller.Controller
import main.model.MinesMapHelper
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {
    val input_without_spaces = input.replaceAll("\\s", "").toLowerCase()
    val col_and_row_index = getRowAndColIndex(input_without_spaces.substring(1))

    val wrong_input_string = "Falsche Eingabe. OXY=Open, FXY=Flag, NXY=Note, S=Solve, Q=Quitt"

    input_without_spaces.head match {
      case 'q' => println("Bis bald!")
      case 'o'=> if(col_and_row_index._1 != -1) controller.openField(col_and_row_index) else println(wrong_input_string)
      case 'f' => if(col_and_row_index._1 != -1) controller.placeFlag(col_and_row_index) else println(wrong_input_string)
      case 'n' => if(col_and_row_index._1 != -1) controller.placeNote(col_and_row_index) else println(wrong_input_string)
      case 's' => controller.solve()
      case _ => println(wrong_input_string)
    }
  }

  def getRowAndColIndex(input:String): (Int,Int) ={
    val mines_map_helper = new MinesMapHelper
    val split_digits_letter = input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")
    if(isInputCorrect(split_digits_letter)) then mines_map_helper.getRolAndColIndex(split_digits_letter(0), split_digits_letter(1), controller.mines_map_base.minesweeper_map(0).length)
    else (-1,-1)
  }
  def isInputCorrect(split_digits_letter:Array[String]): Boolean ={
    if(split_digits_letter.length == 2 &&
      split_digits_letter(0).length == 1 &&
      split_digits_letter(1).forall(_.isDigit) &&
      split_digits_letter(1).length < 3) true
    else false
  }

  override def update: Boolean = { println(controller.gameMapToString);true}

}
