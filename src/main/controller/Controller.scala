package main.controller
import main.model.{GameLogic, GameMap, GameMapString}
import main.util.Observable

class Controller(var gameMap: GameMap, var gameLogic: GameLogic) extends Observable{
  def openField(field_coordinate:String)={
//    gameMap = gameLogic.solve()
    notifyObservers
  }

  def placeFlag(field_coordinate:String)={
//    gameMap = gameLogic.solve()
    notifyObservers
  }
  def placeNote(field_coordinate:String)={
//    gameMap = gameLogic.solve()
    notifyObservers
  }

  def solve()={
//    gameMap = gameLogic.solve()
    notifyObservers
  }

  def gameMapToString: String = gameMap.gameMapToString
}
