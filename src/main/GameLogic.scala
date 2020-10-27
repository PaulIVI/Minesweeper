//class GameLogic {
//  def startTheGame(field_size :Int)={
//    var initial_game_situation = Vector.fill(field_size)(Vector.fill(field_size)(0))
//    initial_game_situation
//  }
//
//  def makeAMove(minesweeper_map: Vector[Vector[Int]], game_situation: Vector[Vector[Int]], game_move_mode : String, game_move: String) = {
//    game_move_mode match {
//      case "open_a_field" => openAField(minesweeper_map, game_situation, game_move)
//      case "place_a_flag" =>
//      case "place_a_maybe_flag" =>
//      case "solved" =>
//      case _ => (minesweeper_map, game_situation, game_move_mode)
//    }
//  }
//
//  def transformLetterToColumnIndex(letter:Char) = {
//    val column_index = letter match {
//      case "a" | "A" => 0
//      case "b" | "B" => 1
//      case "c" | "C" => 2
//      case "d" | "D" => 3
//      case "e" | "E" => 4
//      case "f" | "F" => 5
//      case "g" | "G" => 6
//      case "h" | "H" => 7
//      case "i" | "I" => 8
//      case "j" | "J" => 9
//      case "k" | "K" => 10
//      case "l" | "L" => 11
//      case "m" | "M" => 12
//      case "n" | "N" => 13
//      case "o" | "O" => 14
//      case "p" | "P" => 15
//      case "q" | "Q" => 16
//      case "r" | "R" => 17
//      case "s" | "S" => 18
//      case "t" | "T" => 19
//      case "u" | "U" => 20
//      case "v" | "V" => 21
//      case "w" | "W" => 22
//      case "x" | "X" => 23
//      case "y" | "Y" => 24
//      case "z" | "Z" => 25
//      case _ => "-"
//    }
//    column_index
//  }
//
//  def getColAndRowIndex(game_move:String)={
//
//    val row_and_col_index = if (transformLetterToColumnIndex(game_move.(0)) != "-"){
//      val column_index = transformLetterToColumnIndex(game_move.(0))
//      val row_index = Int(game_move.(1))
//      val row_and_col_index = Tuple2(row_index, column_index)
//      row_and_col_index
//    }else{
//      if(transformLetterToColumnIndex(game_move.(1)) != "-"){
//        val column_index = transformLetterToColumnIndex(game_move.(1))
//        val row_index = Int(game_move(0))
//        val row_and_col_index = Tuple2(row_index, column_index)
//        row_and_col_index
//      }
//    }
//    if (game_move.length != 2 || row_and_col_index(0) not in 0 until 26 || row_and_col_index(1) not in 0 until 26 ){
//      row_and_col_index = Tuple2(-1,-1)
//    }
//  }
//
//  def openAField(minesweeper_map, game_situation, game_move)={
//   //TODO:  test getColAndRowIndex and go on
//  }
//}