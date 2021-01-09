package scala.controller

import main.controller.Controller
import main.model.MinesMap
import main.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerTest extends AnyWordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(new MinesMap(10))
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {
          updated = true; updated
        }
      }
      controller.add(observer)
      "notify its Observer after opening a field" in {
        controller.openField((1, 1))
        observer.update should be(true)
        controller.mines_map.situation(1)(1) should be(1)
      }
      "notify its Observer after place a flag in a field" in {
        controller.placeFlag((3, 3))
        observer.update should be(true)
        controller.mines_map.situation(3)(3) should be(2)
      }
      "notify its Observer after place a note in a field" in {
        controller.placeNote((2, 2))
        observer.update should be(true)
        controller.mines_map.situation(2)(2) should be(3)
      }
    }
  }
}
