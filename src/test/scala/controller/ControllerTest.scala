package controller

import main.controller.Controller
import main.model.MinesMap
import main.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerTest extends AnyWordSpec with Matchers{
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
      "notify its Observer after creation" in {
        controller.openField((1,1))
        observer.updated should be(true)
        controller.mines_map.situation(1)(1) should be(1)
      }
    }
}
}
