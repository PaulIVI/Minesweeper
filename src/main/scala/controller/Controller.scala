package main.controller
import main.model.{MinesMap, MinesMapBase}
import main.util.Observable

class Controller(var mines_map:MinesMap) extends Observable{

  val mines_map_base = new MinesMapBase(10)

  def openField(field_coordinate:(Int, Int))={
    mines_map = mines_map.openField(field_coordinate, mines_map_base)
    notifyObservers
  }

  def placeFlag(field_coordinate:(Int, Int))={
    mines_map = mines_map.placeFlag(field_coordinate)
    notifyObservers
  }
  def placeNote(field_coordinate:(Int, Int))={
    mines_map = mines_map.placeNote(field_coordinate)
    notifyObservers
  }

  def solve()={
    mines_map = mines_map.solve(mines_map_base)
    notifyObservers
  }

  def gameMapToString: String = mines_map.minesMapToString(mines_map_base)
}
