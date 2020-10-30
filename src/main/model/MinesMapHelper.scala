package main.model

class MinesMapHelper {
  def transformLetterToColumnIndex(letter:Char) = {
    val column_index = letter.toUpper match {
      case 'A' => 0
      case 'B' => 1
      case 'C' => 2
      case 'D' => 3
      case 'E' => 4
      case 'F' => 5
      case 'G' => 6
      case 'H' => 7
      case 'I' => 8
      case 'J' => 9
      case 'K' => 10
      case 'L' => 11
      case 'M' => 12
      case 'N' => 13
      case 'O' => 14
      case 'P' => 15
      case 'Q' => 16
      case 'R' => 17
      case 'S' => 18
      case 'T' => 19
      case 'U' => 20
      case 'V' => 21
      case 'W' => 22
      case 'X' => 23
      case 'Y' => 24
      case 'Z' => 25
      case _ => '-'
    }
    column_index
  }

  def getRolAndColIndex(game_move:String)={

    val row_and_col_index = if (transformLetterToColumnIndex(game_move(0)) != '-'){
      val column_index = transformLetterToColumnIndex(game_move(0))
      val row_index = game_move(1).asDigit
      val row_and_col_index = Tuple2(row_index, column_index)
      row_and_col_index
    } else if(transformLetterToColumnIndex(game_move(1)) != '-'){
      val column_index = transformLetterToColumnIndex(game_move(1))
      val row_index = game_move(0).asDigit
      val row_and_col_index = Tuple2(row_index, column_index)
      row_and_col_index
    } else Tuple2(-1,-1)
    if ((game_move.length != 2) || !(0 until 26 contains row_and_col_index._1) || !(0 until 26 contains row_and_col_index._2)){
      Tuple2(-1,-1)
    }else{
      row_and_col_index
    }
  }

  def getCoordinatesAroundField(current_index: (Int, Int)):Vector[(Int, Int)] = {
    val coordinates_around_field = Vector(
      Tuple2(current_index._1 - 1, current_index._2 - 1),
      Tuple2(current_index._1 - 1, current_index._2),
      Tuple2(current_index._1 - 1, current_index._2 + 1),
      Tuple2(current_index._1, current_index._2 - 1),
      Tuple2(current_index._1, current_index._2 + 1),
      Tuple2(current_index._1 + 1, current_index._2 - 1),
      Tuple2(current_index._1 + 1, current_index._2),
      Tuple2(current_index._1 + 1, current_index._2 + 1))
    coordinates_around_field
  }
}
