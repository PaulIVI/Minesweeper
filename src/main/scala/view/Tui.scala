package main.view

import main.controller.Controller
import main.model.{InputLine, MinesMapHelper}
import main.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)

  def processInputLine(input: String):Unit = {

    val verified_col_and_row_index = InputLine(Some(input))
      .removeSpaceAndLowerCase()
      .splitDigitsAndLetters()
      .validateInput()
      .getRowAndColIndex(controller.mines_map_base.minesweeper_map.length)
      .input

    val wrong_input_string = "Falsche Eingabe. OXY=Open, FXY=Flag, NXY=Note, S=Solve, Q=Quitt"
    val input_without_spaces = input.replaceAll("\\s", "").toLowerCase()

    if (verified_col_and_row_index != None || input_without_spaces=="s") {
      verified_col_and_row_index match {
        case Some(data: (Int, Int)) => {
          val col_and_row_index = data
          input_without_spaces.head match {
            case 'q' => println("Bis bald!")
            case 'o' => controller.openField(col_and_row_index)
            case 'f' => controller.placeFlag(col_and_row_index)
            case 'n' => controller.placeNote(col_and_row_index)
            //case 's' => controller.solve
            case _ => println(wrong_input_string)
          }
        }
        case None =>{
          if (input_without_spaces == "s") controller.solve()
          else println("Unknown data type")

        }
        case _ => println("Unknown data type")
      }
    } else println(wrong_input_string)
  }

  override def update: Boolean = { println(controller.gameMapToString);true}

}
