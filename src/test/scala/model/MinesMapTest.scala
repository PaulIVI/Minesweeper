package scala.model

import main.model.MinesMap
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MinesMapTest extends AnyWordSpec with Matchers{
  val example_game_map = Vector(Vector(0,1,2,3),Vector(4,5,9,7),Vector(8,9,0,1),Vector(2,3,4,5))
  val example_current_game_map = Vector(Vector(0,1,2,3),Vector(1,2,3,0),Vector(2,3,0,1),Vector(3,0,1,2))

  val mines_map = new MinesMap(example_current_game_map)

  "The Function openField" should{
    "open all cells in the grid if a mine is opend" in{
      //todo implement the missing test. Not possible right now, cause of random minesmap which is generated in MinesMapBase.
    }
  }
}
