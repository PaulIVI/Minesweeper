package scala.view

import main.controller.Controller
import main.model.MinesMap
import main.view.Tui
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuiTest extends AnyWordSpec with Matchers{
  "A Minesweeper Tui" should {
    val controller = new Controller(new MinesMap(10))
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    "do nothing on input '123456'" in {
      tui.processInputLine("123456")
    }
    "do nothing on input 'qw5'" in {
      tui.processInputLine("qw5")
    }
    "open one field in the game map on input 'ob2' " in{
      tui.processInputLine("ob2")
      controller.mines_map.situation(2)(1)should be(1)
    }
    "open one field in the game map on input 'OC4' " in{
      tui.processInputLine("OC4")
      controller.mines_map.situation(3)(3)should be(1)
    }
    "do nothing on input ob22" in{
      tui.processInputLine("ob22")
    }
    "set one field in the game map to '2' on input 'fc3' and back to '0' if it is called again" in{
      tui.processInputLine("fc3")
      controller.mines_map.situation(3)(2)should be(2)
      tui.processInputLine("fc3")
      controller.mines_map.situation(3)(2)should be(0)
    }
    "set one field in the game map to '3' on input 'nd4' and back to '0' if it is called again" in{
      tui.processInputLine("nd4")
      controller.mines_map.situation(4)(3)should be(3)
      tui.processInputLine("nd4")
      controller.mines_map.situation(4)(3)should be(0)
    }
    "the function getRowAndColIndex should return (-1,-1) on input 'a'" in{
      tui.getRowAndColIndex("a") should be((-1,-1))
    }
    "the function getRowAndColIndex should return (3,0) on input 'a3'" in{
      tui.getRowAndColIndex("a3") should be((3,0))
    }
    "the function getRowAndColIndex should return (-1,-1) on input 'abcdef123'" in{
      tui.getRowAndColIndex("abcdef123") should be((-1,-1))
    }
    "the function getRowAndColIndex should return (-1,-1) on input '22'" in{
      tui.getRowAndColIndex("22") should be((-1,-1))
    }
    "the function getRowAndColIndex should return (-1,-1) on input 'bb'" in{
      //todo fix this problem
      tui.getRowAndColIndex("bb") should be((-1,-1))
    }

  }
}
