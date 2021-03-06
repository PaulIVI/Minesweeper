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
    "Return an Info if the inputString is wrong" in {
      tui.processInputLine("xd4")
    }


    "open one field in the game map on input 'ob2' " in {
      tui.processInputLine("ob2")
      controller.mines_map.situation(2)(1) should be(1)
    }
    "open one field in the game map on input 'OC4' " in {
      tui.processInputLine("OC4")
      controller.mines_map.situation(4)(2) should be(1)
    }
    "place a flag in the game map on input 'fa2' " in {
      tui.processInputLine("fa2")
      controller.mines_map.situation(2)(0) should be(2)
    }
    "do nothing on input ob22" in {
      tui.processInputLine("ob22")
    }
    "set one field in the game map to '2' on input 'fc3' and back to '0' if it is called again" in {
      tui.processInputLine("fc3")
      controller.mines_map.situation(3)(2) should be(2)
      tui.processInputLine("fc3")
      controller.mines_map.situation(3)(2) should be(0)
    }
    "set one field in the game map to '3' on input 'nd4' and back to '0' if it is called again" in {
      tui.processInputLine("nd4")
      controller.mines_map.situation(4)(3) should be(3)
      tui.processInputLine("nd4")
      controller.mines_map.situation(4)(3) should be(0)
    }
    "open all fields in the game map if the player loose"in{
      tui.processInputLine("nd4")
      controller.mines_map.situation(4)(3) should be(3)
      tui.processInputLine("s")
      controller.mines_map.situation.map(n => n.map(m =>{
        m should be(1)
      }))
    }
    "fill the hole game map with '!' if the input is 's' and the player won" in{
      val controller_loose = new Controller(new MinesMap(10))
      val tui_loose = new Tui(controller_loose)
      controller_loose.mines_map_base.minesweeper_map.zipWithIndex.map{
        case (n, n_index) => n.zipWithIndex.map{
          case(m, m_index) =>{
            if(m == 9) controller_loose.placeFlag((n_index, m_index))
            else controller_loose.openField((n_index, m_index))
      }}}
      tui_loose.processInputLine("s")
      controller_loose.mines_map.situation.map(n => n.map(m =>{
        m should be(2)
      }))
    }
  }
}
