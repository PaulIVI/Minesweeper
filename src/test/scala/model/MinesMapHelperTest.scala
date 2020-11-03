package scala.model

import main.model.MinesMapHelper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MinesMapHelperTest extends AnyWordSpec with Matchers {
  val mines_map_helper = new MinesMapHelper
  "The Function transformLetterToColumnIndex" should{
    "always return the right Index for the letter" in{
      val letters =List('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
      letters.zipWithIndex.foreach{
        case (letter, index) => {
          mines_map_helper.transformLetterToColumnIndex(letter) should be(index)
        }
      }
    }
    "Return '-' if the input is not a letter" in{
      mines_map_helper.transformLetterToColumnIndex('?') should be('-')
    }
  }
  "The function getRolAndColIndex" should{
    "Return (1,1) for the input 'A0'" in{
      mines_map_helper.getRolAndColIndex("A","0", 10) should be((0,0))
    }
    "Return (-1,-1) for the input 'B?'" in{
      mines_map_helper.getRolAndColIndex("B","99", 10) should be((-1,-1))
    }
    "Return (-1,-1) for the input '?B'" in{
      mines_map_helper.getRolAndColIndex("?","80", 10) should be((-1,-1))
    }
  }

}
