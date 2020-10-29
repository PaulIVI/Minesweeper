package main.controller
import main.model.{MinesMapBase, MinesMap}
import main.util.Observable

class Controller(var mines_map:MinesMap) extends Observable{

  val mines_map_base = new MinesMapBase(10)

  def openField(field_coordinate:(Int, Int))={
    mines_map = mines_map.openField(field_coordinate, mines_map_base)
    notifyObservers
  }

  def placeFlag(field_coordinate:(Int, Int))={
    notifyObservers
  }
  def placeNote(field_coordinate:(Int, Int))={
    notifyObservers
  }

  def solve()={
    notifyObservers
  }

  def gameMapToString: String = mines_map.minesMapToString(mines_map_base)
}
