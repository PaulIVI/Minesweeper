object Minesweeper {
  def main(args: Array[String]) = {
    val difficulty = 10

    val game_map = Vector(Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0), Vector(1,0,0,0,1,0))
    val current_game_map = Vector(Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1), Vector(0,1,2,3,0,1))

    val user_interface = new UI
    val console_string = user_interface.gameMapToString(game_map, current_game_map)
    println(console_string)

    val mine = new GameMap
    val mapMine = mine.createMap(difficulty)

//    val logic = new GameLogic
//    val game_situation = logic.startTheGame(difficulty)
//
//    printf(mapMine.toString())
//    printf(game_situation.toString())
  }
}