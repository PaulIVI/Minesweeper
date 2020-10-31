package scala.model

import main.model.{MinesMap, MinesMapBase}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MinesMapTest extends AnyWordSpec with Matchers{
  val example_game_map = Vector(Vector(0,1,2,3),Vector(0,0,9,7),Vector(0,9,0,1),Vector(2,3,4,5))
  val example_current_game_map = Vector(Vector(0,1,2,3),Vector(1,2,3,0),Vector(2,3,0,1),Vector(3,0,1,2))

  val mines_map = new MinesMap(example_current_game_map)

  "The Function getEveryConnectedZero" should{
    "return all connected zeros in the map for one field with zero" in{
      val already_collected_zeros = Vector((0,0))
      val already_collected_zeros_but_not_checked = Vector((0,0))
      val return_value_should_be = Vector((0,0),(1,0),(1,1),(2,0),(2,2))
      mines_map.getEveryConnectedZero(already_collected_zeros, already_collected_zeros_but_not_checked, example_game_map) should be(return_value_should_be)
    }
  }

  "The Function openField" should{
    "open all cells in the grid if a mine is opend" in{
      val opend_mines_map = mines_map.openField((1,2), example_game_map)
      opend_mines_map.situation.map(n => n.map(m =>{
        m should be(1)
      }))
    }
    "open one cell in the grid if no mine is opend" in{
      val opend_mines_map = mines_map.openField((1,1), example_game_map)
      opend_mines_map.situation(1)(1) should be(1)
    }
  }
}
