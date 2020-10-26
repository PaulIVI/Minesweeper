object Minesweeper {
  def main(args: Array[String]) = {

    val mine = new GameMap
    val mapMine = mine.create_map(10)
    printf(mapMine.toString())
  }
}