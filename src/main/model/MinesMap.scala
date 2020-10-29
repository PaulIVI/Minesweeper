package main.model

case class MinesMap(situation: Vector[Vector[Int]]) {
  def this(difficulty: Int) = this(Vector.fill(difficulty)(Vector.fill(difficulty)(0)))

  val helper = new MinesMapHelper
  val printer = new MinesMapString

  def openField(row_and_col_index: (Int, Int), mines_map_base: MinesMapBase): MinesMap = {
    copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 1)))
  }

  def minesMapToString(mines_map_base: MinesMapBase): String ={
    printer.gameMapToString(mines_map_base.minesweeper_map, situation)
  }

}
