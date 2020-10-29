package main.model

case class MinesMap(situation: Vector[Vector[Int]]) {
  def this(difficulty: Int) = this(Vector.fill(difficulty)(Vector.fill(difficulty)(0)))

  val helper = new MinesMapHelper
  val printer = new MinesMapString

  def openField(row_and_col_index: Tuple2[Int, Int], mines_map_base: MinesMapBase): MinesMap = {
    if(mines_map_base.minesweeper_map(row_and_col_index._1)(row_and_col_index._2)==9){
      copy(Vector.fill(mines_map_base.minesweeper_map.length)(Vector.fill(mines_map_base.minesweeper_map.length)(1)))
    }else{
      /*if(mines_map_base.minesweeper_map(row_and_col_index._1)(row_and_col_index._2)==0){

      }else{

       */
      copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 1)))

    }
  }

  def addCordinatesWhichAreNotAddedYet(coordinates:Vector[(Int,Int)], coordinate_to_add:Vector[(Int,Int)], mines_map_base: MinesMapBase, added_zeros:Vector[(Int)])={
    if (coordinate_to_add.isEmpty){
      (coordinates, added_zeros)
    }else{
      if (!(coordinates.contains(coordinate_to_add(0)))){
        if (mines_map_base.minesweeper_map(coordinate_to_add(0)._1)(coordinate_to_add(0)._2))
        addCordinatesWhichAreNotAddedYet(coordinates:+coordinate_to_add(0), coordinate_to_add.drop(0), added_zeros)
      }else{
        addCordinatesWhichAreNotAddedYet(coordinates, coordinate_to_add.drop(0), added_zeros)
    }
  }}

  def checkForZeros(coordinates_around_field: Vector[(Int, Int)], already_collected_zeros:Vector[(Int, Int)], already_collected_but_not_checked_zeros:Vector[(Int, Int)], mines_map_base: MinesMapBase):(Vector[(Int,Int)],Vector[(Int,Int)])={
    if (coordinates_around_field.isEmpty){
      (already_collected_zeros, already_collected_but_not_checked_zeros)
    }else{
      if(!(already_collected_zeros.contains(coordinates_around_field(0))) && mines_map_base.minesweeper_map(coordinates_around_field(0)._1)(coordinates_around_field(0)._2)==0) {
          checkForZeros(coordinates_around_field.drop(0), already_collected_zeros:+coordinates_around_field(0), already_collected_but_not_checked_zeros:+coordinates_around_field(0), mines_map_base)
      } else{
          checkForZeros(coordinates_around_field.drop(0), already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
        }
      }
  }



  def getEveryConnectedZero(already_collected_zeros:Vector[(Int, Int)], already_collected_but_not_checked_zeros:Vector[(Int, Int)], mines_map_base: MinesMapBase):Vector[(Int,Int)]={
    val coordinates_around_field = helper.getCoordinatesAroundField(already_collected_but_not_checked_zeros(0))
    val already_collected_zeros_and_unchecked = checkForZeros(coordinates_around_field, already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
    if (already_collected_but_not_checked_zeros.isEmpty){
      already_collected_zeros_and_unchecked._1
    }else{
      getEveryConnectedZero(already_collected_zeros_and_unchecked._1, already_collected_zeros_and_unchecked._2.drop(0), mines_map_base)
    }
  }

  def getFieldsToOpenBecause0(row_and_col_index: Tuple2[Int, Int], mines_map_base: MinesMapBase, already_open:Vector[Tuple2[Int, Int]])={
    val coordinates_around_field = helper.getCoordinatesAroundField(row_and_col_index)


  }

  def placeFlag(row_and_col_index: Tuple2[Int, Int])={
    if(situation(row_and_col_index._1)(row_and_col_index._2)==2){
      copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 0)))
    }else {
      copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 2)))
    }
  }
  def placeNote(row_and_col_index: Tuple2[Int, Int])={
    if(situation(row_and_col_index._1)(row_and_col_index._2)==3){
      copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 0)))
    }else {
      copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 3)))
    }
  }

  def solve(mines_map_base: MinesMapBase)={
    val solved_map = situation.zipWithIndex.map{
      case (row,row_index) =>
        row.zipWithIndex.map{
          case (value, value_index) =>
            if(value==3){3}
            else if((value == 2 && mines_map_base.minesweeper_map(row_index)(value_index)!= 9) || value == 0){3}
            else 2
        }
    }
    println(solved_map)
    if (solved_map.flatten.contains(3)){
      copy(Vector.fill(mines_map_base.minesweeper_map.length)(Vector.fill(mines_map_base.minesweeper_map.length)(1)))
    }
    else{
      copy(Vector.fill(mines_map_base.minesweeper_map.length)(Vector.fill(mines_map_base.minesweeper_map.length)(2)))
    }

  }


  def minesMapToString(mines_map_base: MinesMapBase): String ={
    printer.gameMapToString(mines_map_base.minesweeper_map, situation)
  }

}
