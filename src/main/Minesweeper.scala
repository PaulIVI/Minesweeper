object Minesweeper {
  def main(args: Array[String]) = {
    val difficulty = 10

    val mine = new GameMap
    val mapMine = mine.create_map(difficulty)

    val logic = new GameLogic
    val game_situation = logic.start_the_game(difficulty)

    printf(mapMine.toString())
    printf(game_situation.toString())
  }
}