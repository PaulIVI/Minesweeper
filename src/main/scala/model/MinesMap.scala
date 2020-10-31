package main.model

import main.model.{MinesMapHelper, MinesMapString}

case class MinesMap(situation: Vector[Vector[Int]]) {
  def this(difficulty: Int) = this(Vector.fill(difficulty)(Vector.fill(difficulty)(0)))

  val helper = new MinesMapHelper
  val printer = new MinesMapString

  def openField(row_and_col_index: Tuple2[Int, Int], mines_map_base: MinesMapBase): MinesMap = {
    if (mines_map_base.minesweeper_map(row_and_col_index._1)(row_and_col_index._2) == 9) {
      copy(Vector.fill(mines_map_base.minesweeper_map.length)(Vector.fill(mines_map_base.minesweeper_map.length)(1)))
    } else {
      if (mines_map_base.minesweeper_map(row_and_col_index._1)(row_and_col_index._2) == 0) {
        val fields_to_open = getFieldsToOpenBecause0(row_and_col_index, mines_map_base)

        val new_situation = situation.zipWithIndex.map { case (row, row_index) =>
          row.zipWithIndex.map{ case (value, value_index) =>
            if (fields_to_open.contains((row_index, value_index))) {
              1
            }else{
              value
            }
          }
        }
        copy(new_situation)
      } else {
        copy(situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 1)))
      }
    }
  }


  def checkForZeros(coordinates_around_field: Vector[(Int, Int)], already_collected_zeros:Vector[(Int, Int)], already_collected_but_not_checked_zeros:Vector[(Int, Int)], mines_map_base: MinesMapBase):(Vector[(Int,Int)],Vector[(Int,Int)])={
    if (coordinates_around_field.isEmpty){
      (already_collected_zeros, already_collected_but_not_checked_zeros)
    }else{
      if((mines_map_base.minesweeper_map.indices contains(coordinates_around_field(0)._1)) && (mines_map_base.minesweeper_map.indices contains(coordinates_around_field(0)._2))) {
        if (!(already_collected_zeros.contains(coordinates_around_field(0))) && mines_map_base.minesweeper_map(coordinates_around_field(0)._1)(coordinates_around_field(0)._2) == 0) {
          checkForZeros(coordinates_around_field.drop(1), already_collected_zeros :+ coordinates_around_field(0), already_collected_but_not_checked_zeros :+ coordinates_around_field(0), mines_map_base)
        }else{
          checkForZeros(coordinates_around_field.drop(1), already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
        }
      }else{
          checkForZeros(coordinates_around_field.drop(1), already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
        }
      }
  }



  def getEveryConnectedZero(already_collected_zeros:Vector[(Int, Int)], already_collected_but_not_checked_zeros:Vector[(Int, Int)], mines_map_base: MinesMapBase):Vector[(Int,Int)]={
    if(already_collected_but_not_checked_zeros.isEmpty){
      already_collected_zeros
    }else{
      val coordinates_around_field = helper.getCoordinatesAroundField(already_collected_but_not_checked_zeros(0))
      val already_collected_zeros_and_unchecked = checkForZeros(coordinates_around_field, already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
      if (already_collected_but_not_checked_zeros.isEmpty){
        already_collected_zeros_and_unchecked._1
      }else{
        getEveryConnectedZero(already_collected_zeros_and_unchecked._1, already_collected_zeros_and_unchecked._2.drop(1), mines_map_base)
      }
    }
  }

  def getFieldsAroundZero(every_connected_zero:Vector[(Int, Int)], fields_to_open:Vector[(Int,Int)]):Vector[(Int,Int)]= {
    if (every_connected_zero.isEmpty) {
      fields_to_open.distinct
    }else{
      val coordinates_around_field = helper.getCoordinatesAroundField(every_connected_zero(0))
      getFieldsAroundZero(every_connected_zero.drop(1), fields_to_open++coordinates_around_field)
    }
  }

  def getFieldsToOpenBecause0(row_and_col_index: (Int, Int), mines_map_base:MinesMapBase)={
    val every_connected_zero = getEveryConnectedZero(Vector(row_and_col_index), Vector(row_and_col_index),mines_map_base)
    val fields_to_open = getFieldsAroundZero(every_connected_zero, Vector(every_connected_zero(0)))
    fields_to_open
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
