package main.model

case class InputLine(input:Option[Any]){

  def removeSpaceAndLowerCase(): InputLine = input match {
    case Some(input_ : String) =>
      val input_without_first_char = input_.replaceAll("\\s", "").toLowerCase().drop(1)
      if (input_without_first_char.length >1) copy(Some(input_without_first_char))
      else copy(None)
    case None => copy(None)
  }

  def splitDigitsAndLetters(): InputLine = input match {
    case Some(input_ : String) => copy(Some(input_.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")))
    case None => copy(None)
  }

  def validateInput(): InputLine = input match {
    case Some(input_ : Array[String]) =>
      if(input_.length == 2 &&
        input_(0).length == 1 &&
        input_(1).forall(_.isDigit) &&
        input_(1).length < 3) copy(Some(input_))
      else copy(None)
    case None => copy(None)
  }

  def getRowAndColIndex(mines_map_size:Int): InputLine = input match {
    case Some(input_ : Array[String]) =>
      val mines_map_helper = new MinesMapHelper
      val row_and_col_index = mines_map_helper.getRolAndColIndex(input_(0), input_(1), mines_map_size)
      if (row_and_col_index._1 != -1) copy(Some(row_and_col_index))
      else copy(None)
    case None => copy(None)

  }
}