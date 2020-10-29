package view

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
    "do nothing on input 'qw5'" in {
      tui.processInputLine("qw5")
    }
    "open one field in the game map on input 'ob2' " in{
      tui.processInputLine("ob2")
      controller.mines_map.situation(2)(1)should be(1)
    }
    "do nothing on input ob22" in{
      tui.processInputLine("ob22")
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
