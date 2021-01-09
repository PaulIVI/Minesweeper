package main.model

import main.model.MinesMapHelper

class MinesMapBase(difficulty: Int):
  val r = scala.util.Random
  val probability_for_mine = 0.16
  val mine_map = Vector.fill(difficulty)(Vector.fill(difficulty)(
    if (r.nextFloat < probability_for_mine) 9
    else
      0
    )
  )

  val num_of_mines = mine_map.flatten.count(_ == 9)

  val index_of_mines =
    for i <- 0 until mine_map.length; j <- 0 until mine_map(i).length
         if (mine_map(i)(j) == 9)
    yield (i, j)

  val minesweeper_map_incremented = incrementAroundAllMines(mine_map, index_of_mines, 0)
  val minesweeper_map = minesweeper_map_incremented.map(row => row.map(value => if(value>9)9 else value))

  minesweeper_map

  def incrementFieldsAroundSpecificMine(mine_map: Vector[Vector[Int]], coordiantes_to_increment: Vector[Tuple2[Int, Int]], current_cordinate_index: Int): Vector[Vector[Int]] = 
    if (current_cordinate_index >= coordiantes_to_increment.length) then mine_map
    else
      val inceremted_map = mine_map.zipWithIndex.map {
        case (row, row_index) => row.zipWithIndex.map {
          case (field_value, column_index) =>
            if (row_index == coordiantes_to_increment(current_cordinate_index)._1 && column_index == coordiantes_to_increment(current_cordinate_index)._2) field_value + 1
            else field_value
        }
      }
      val next_increment = incrementFieldsAroundSpecificMine(inceremted_map, coordiantes_to_increment, current_cordinate_index + 1)
      next_increment

  def incrementAroundAllMines(mine_map: Vector[Vector[Int]], index_of_mines: IndexedSeq[(Int, Int)], current_mine_index: Int): Vector[Vector[Int]] = 
    val helper = new MinesMapHelper
    if (current_mine_index < index_of_mines.length) then
      val coordinates_to_increment = helper.getCoordinatesAroundField(index_of_mines(current_mine_index))
      val minesweeper_map_incremented = incrementFieldsAroundSpecificMine(mine_map, coordinates_to_increment, 0)
      val minesweeper_map_next_increment = incrementAroundAllMines(minesweeper_map_incremented, index_of_mines, current_mine_index + 1)
      minesweeper_map_next_increment
    else 
      mine_map











