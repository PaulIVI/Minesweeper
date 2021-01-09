package main.model

case class MinesMap(situation: Vector[Vector[Int]]):
  def this(difficulty: Int) = this(Vector.fill(difficulty)(Vector.fill(difficulty)(0)))

  val helper = new MinesMapHelper
  val printer = new MinesMapString

  def openField(row_and_col_index: (Int, Int), mines_map_base: Vector[Vector[Int]]): MinesMap = 
    if (field_is_bomb(row_and_col_index, mines_map_base)) then copy(game_lost(mines_map_base))   
    else 
      if (field_is_zero(row_and_col_index, mines_map_base)) openSeveralFields(row_and_col_index, mines_map_base)
      else openOneField(row_and_col_index)
    
  

  def placeFlag(row_and_col_index: (Int, Int)) = 
    if (field_is_flag(row_and_col_index)) copy(remove_item_and_close_field(row_and_col_index))
    else copy(fieldToFlag(row_and_col_index))
  

  def placeNote(row_and_col_index: (Int, Int)) = 
    if (field_is_note(row_and_col_index)) copy(remove_item_and_close_field(row_and_col_index))
    else copy(fieldToNote(row_and_col_index))
  

  def solve(mines_map_base: MinesMapBase) = 
    val evaluation = getEvaluationOfSituation(mines_map_base.minesweeper_map)
    if (gameIsLost(evaluation)) copy(game_lost(mines_map_base.minesweeper_map))
    else copy(gameWon(mines_map_base.minesweeper_map))
  

  def minesMapToString(mines_map_base: MinesMapBase): String = 
    printer.gameMapToString(mines_map_base.minesweeper_map, situation)
  

  def field_is_bomb(row_and_col_index: (Int, Int), mines_map_base: Vector[Vector[Int]]) = 
    if (mines_map_base(row_and_col_index._1)(row_and_col_index._2) == 9) true
    else false
  

  def field_is_zero(row_and_col_index: (Int, Int), mines_map_base: Vector[Vector[Int]]) = 
    if (mines_map_base(row_and_col_index._1)(row_and_col_index._2) == 0) true
    else false
  

  def field_is_flag(row_and_col_index: (Int, Int)) = 
    if (situation(row_and_col_index._1)(row_and_col_index._2) == 2) true
    else false
  

  def field_is_note(row_and_col_index: (Int, Int)) = 
    if (situation(row_and_col_index._1)(row_and_col_index._2) == 3) true
    else false
  

  def fieldIsClosed(row_and_col_index: (Int, Int)) = 
    if (situation(row_and_col_index._1)(row_and_col_index._2) == 0) true
    else false
  

  def gameIsLost(evaluation: Vector[Vector[Int]]) = 
    if (evaluation.flatten.contains(3)) true
    else false
  

  def remove_item_and_close_field(row_and_col_index: (Int, Int)) = 
    situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 0))
  

  def fieldToFlag(row_and_col_index: (Int, Int)) = 
    situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 2))
  

  def fieldToNote(row_and_col_index: (Int, Int)) = 
    situation.updated(row_and_col_index._1, situation(row_and_col_index._1).updated(row_and_col_index._2, 3))
  

  def game_lost(mines_map_base: Vector[Vector[Int]]) = 
    Vector.fill(mines_map_base.length)(Vector.fill(mines_map_base.length)(1))
  

  def gameWon(mines_map_base: Vector[Vector[Int]]) = 
    Vector.fill(mines_map_base.length)(Vector.fill(mines_map_base.length)(2))
  

  def openSeveralFields(row_and_col_index: (Int, Int), mines_map_base: Vector[Vector[Int]]) = 
    val fields_to_open = getFieldsToOpenBecause0(row_and_col_index, mines_map_base)
    val new_situation = get_updated_situation(fields_to_open)
    copy(new_situation)
  

  def openOneField(row_and_col_index: (Int, Int)) = 
    val field_to_open = Vector(row_and_col_index)
    copy(get_updated_situation(field_to_open))
  

  def get_updated_situation(fields_to_open: Vector[(Int, Int)]) = 
    situation.zipWithIndex.map { case (row, row_index) =>
      row.zipWithIndex.map { case (value, value_index) =>
        if (fields_to_open.contains((row_index, value_index))) then
          1
        else
          value
      }
    }
  

  def getEvaluationOfSituation(mines_map_base: Vector[Vector[Int]]) = 
    situation.zipWithIndex.map {
      case (row, row_index) =>
        row.zipWithIndex.map {
          case (value, col_index) =>
            if (field_is_note((row_index, col_index))) 3
            else if ((field_is_flag((row_index, col_index)) && !field_is_bomb((row_index, col_index), mines_map_base)) || fieldIsClosed((row_index, col_index))) 3
            else 2
        }
    }
  

  def checkForZeros(coordinates_around_field: Vector[(Int, Int)], already_collected_zeros: Vector[(Int, Int)], already_collected_but_not_checked_zeros: Vector[(Int, Int)], mines_map_base: Vector[Vector[Int]]): (Vector[(Int, Int)], Vector[(Int, Int)]) = 
    if (coordinates_around_field.isEmpty) then (already_collected_zeros, already_collected_but_not_checked_zeros)
    else
      if ((mines_map_base.indices contains (coordinates_around_field(0)._1)) && (mines_map_base.indices contains (coordinates_around_field(0)._2))) then 
        if (!(already_collected_zeros.contains(coordinates_around_field(0))) && mines_map_base(coordinates_around_field(0)._1)(coordinates_around_field(0)._2) == 0) then
          checkForZeros(coordinates_around_field.drop(1), already_collected_zeros :+ coordinates_around_field(0), already_collected_but_not_checked_zeros :+ coordinates_around_field(0), mines_map_base)
        else
          checkForZeros(coordinates_around_field.drop(1), already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
      else
        checkForZeros(coordinates_around_field.drop(1), already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)

  def getEveryConnectedZero(already_collected_zeros: Vector[(Int, Int)], already_collected_but_not_checked_zeros: Vector[(Int, Int)], mines_map_base: Vector[Vector[Int]]): Vector[(Int, Int)] = 
    if (already_collected_but_not_checked_zeros.isEmpty) then
      already_collected_zeros
    else
      val coordinates_around_field = helper.getCoordinatesAroundField(already_collected_but_not_checked_zeros(0))
      val already_collected_zeros_and_unchecked = checkForZeros(coordinates_around_field, already_collected_zeros, already_collected_but_not_checked_zeros, mines_map_base)
      getEveryConnectedZero(already_collected_zeros_and_unchecked._1, already_collected_zeros_and_unchecked._2.drop(1), mines_map_base)

  def getFieldsAroundZero(every_connected_zero: Vector[(Int, Int)], fields_to_open: Vector[(Int, Int)]): Vector[(Int, Int)] = 
    if (every_connected_zero.isEmpty) then fields_to_open.distinct
    else
      val coordinates_around_field = helper.getCoordinatesAroundField(every_connected_zero(0))
      getFieldsAroundZero(every_connected_zero.drop(1), fields_to_open ++ coordinates_around_field)
  

  def getFieldsToOpenBecause0(row_and_col_index: (Int, Int), mines_map_base: Vector[Vector[Int]]) = 
    val every_connected_zero = getEveryConnectedZero(Vector(row_and_col_index), Vector(row_and_col_index), mines_map_base)
    val fields_to_open = getFieldsAroundZero(every_connected_zero, Vector(every_connected_zero(0)))
    fields_to_open

