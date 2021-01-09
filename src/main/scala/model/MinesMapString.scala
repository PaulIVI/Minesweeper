package main.model

class MinesMapString{
  def gameMapToString(game_map:Vector[Vector[Int]], current_game_map:Vector[Vector[Int]]):String =
    val first_row = generateFirstRow(game_map)
    val rest_of_grid = generateRestOfGrid(game_map, current_game_map)
    first_row + rest_of_grid

  def generateRestOfGrid(game_map:Vector[Vector[Int]], current_game_map:Vector[Vector[Int]]): String =
    val string_list_from_current_game_map = current_game_map.zipWithIndex.map{
      case (row, row_index) => row.zipWithIndex.map {
        case (value, column_index) => addVectorElementToString(value, row, row_index, column_index, game_map)
      }
    }
    val current_game_map_string = string_list_from_current_game_map.map(n => n.mkString("")).mkString("")
    current_game_map_string
  
  def addVectorElementToString(value:Int, row:Vector[Int], row_index:Int, column_index:Int, game_map:Vector[Vector[Int]]): String =
    val result1 = if (column_index == 0) row_index + " " else ""
    val result2 = result1 + returnVisibleChar(value, row_index, column_index, game_map)
    val result3 = if(column_index == row.length-1) result2 + "\n" else result2
    result3

  def generateFirstRow(game_map:Vector[Vector[Int]]): String =
    val abc = Vector("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    val abc_correct_length = abc.slice(0, game_map.length)
    val first_row = "  " + abc_correct_length.mkString(" ") + " \n"
    first_row
  
  def returnVisibleChar(value:Int, row_index:Int, column_index:Int, game_map:Vector[Vector[Int]]) =
    value match {
      case 0 => "# "
      case 1 => game_map(row_index)(column_index) + " "
      case 2 => "! "
      case 3 => "? "
      case _ => "E "
    }
}