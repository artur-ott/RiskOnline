package scala_risk

object Colors extends Enumeration {
  type Colors = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._

case class Player(name : String, color : Colors) {
}