package main.model

case class MinesMap(situation: Vector[Vector[Int]]) {
  def this(difficulty: Int) = this(Vector.fill(difficulty)(Vector.fill(difficulty)(0)))

  val helper = new MinesMapHelper
  val printer = new MinesMapString

  def openField(game_move: String, mines_map_base: MinesMapBase): MinesMap = {
    val row_and_col_index = helper.getRolAndColIndex(game_move)
    copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 1)))
  }

  def minesMapToString(mines_map_base: MinesMapBase): String ={
    printer.gameMapToString(mines_map_base.minesweeper_map, situation)
  }

}
