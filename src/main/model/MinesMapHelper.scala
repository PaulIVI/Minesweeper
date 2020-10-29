package main.model

class MinesMapHelper {
  def transformLetterToColumnIndex(letter:Char) = {
    val column_index = letter match {
      case 'a' | 'A' => 0
      case 'b' | 'B' => 1
      case 'c' | 'C' => 2
      case 'd' | 'D' => 3
      case 'e' | 'E' => 4
      case 'f' | 'F' => 5
      case 'g' | 'G' => 6
      case 'h' | 'H' => 7
      case 'i' | 'I' => 8
      case 'j' | 'J' => 9
      case 'k' | 'K' => 10
      case 'l' | 'L' => 11
      case 'm' | 'M' => 12
      case 'n' | 'N' => 13
      case 'o' | 'O' => 14
      case 'p' | 'P' => 15
      case 'q' | 'Q' => 16
      case 'r' | 'R' => 17
      case 's' | 'S' => 18
      case 't' | 'T' => 19
      case 'u' | 'U' => 20
      case 'v' | 'V' => 21
      case 'w' | 'W' => 22
      case 'x' | 'X' => 23
      case 'y' | 'Y' => 24
      case 'z' | 'Z' => 25
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
