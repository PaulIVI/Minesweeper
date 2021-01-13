package main.view

import main.controller.Controller
import main.model.{InputLine, MinesMapHelper}
import main.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String): Unit = {

    val verified_col_and_row_index = InputLine(Some(input))
      .removeSpaceAndLowerCase()
      .splitDigitsAndLetters()
      .validateInput()
      .getRowAndColIndex(controller.mines_map_base.minesweeper_map.length)
      .input

    val wrong_input_string = "Falsche Eingabe. OXY=Open, FXY=Flag, NXY=Note, S=Solve, Q=Quitt"
    val input_without_spaces = input.replaceAll("\\s", "").toLowerCase()

    verified_col_and_row_index match {
      case Some(data: (Int, Int)) => {
        val col_and_row_index = data
        input_without_spaces.head match {
          case 'o' => controller.openField(col_and_row_index)
          case 'f' => controller.placeFlag(col_and_row_index)
          case 'n' => controller.placeNote(col_and_row_index)
          case _ => println(wrong_input_string)
        }
      }
      case None => {
        if (input_without_spaces == "s") controller.solve()
        else if (input_without_spaces == "q") println("Bis bald!")
        else println("Keine korrekte Eingabe. Oxy = Feld öffnen. Nxy = Feld merken. Fxy = Feld als Bombe markieren.")

      }
      case _ => println("Keine korrekte Eingabe. Oxy = Feld öffnen. Nxy = Feld merken. Fxy = Feld als Bombe markieren.")
    }
  }


  override def update: Boolean = {
    println(controller.gameMapToString); true
  }

}
