package scala.model

import main.model.MinesMapString
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MinesMapStringTest extends AnyWordSpec with Matchers{
  val example_game_map = Vector(Vector(0,1,2,3),Vector(4,5,6,7),Vector(8,9,0,1),Vector(2,3,4,5))
  val example_current_game_map = Vector(Vector(0,1,2,3),Vector(1,2,3,0),Vector(2,3,0,1),Vector(3,0,1,2))
  val printer = new MinesMapString

  "The Function returnVisbleChar" should{
    "Return '# ' on the input (value) 0" in{
      printer.returnVisibleChar(0, 1, 1, example_game_map) should be("# ")
    }
    "Return '5 ' on the input (value = 1, row_index = 1, column_index = 1)" in{
      printer.returnVisibleChar(1, 1, 1, example_game_map) should be("5 ")
    }
    "Return '! ' on the input (value) 2" in{
      printer.returnVisibleChar(2, 1, 1, example_game_map) should be("! ")
    }
    "Return '? ' on the input (value) 3" in{
      printer.returnVisibleChar(3, 1, 1, example_game_map) should be("? ")
    }
    "Return 'E ' on the input (value) 8" in{
      printer.returnVisibleChar(8, 1, 1, example_game_map) should be("E ")
    }
  }
  "The Function generateFirstRow" should{
    "Return '  A B C D \n' on the input with gridsize 4x4" in{
      printer.generateFirstRow(example_game_map) should be ("  A B C D \n")
    }
  }
  "The Function gameMapToString" should{
    "Return a grid with 4x4" in{
      val game_map_string = "  A B C D \n0 # 1 ! ? \n1 4 ! ? # \n2 ! ? # 1 \n3 ? # 4 ! \n"
      printer.gameMapToString(example_game_map, example_current_game_map) should be(game_map_string)
    }
  }
}
