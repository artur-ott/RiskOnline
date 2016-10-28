package scala_risk

case class Player(name : String, color : Colors) {
  
}

class Colors extends Enumeration {
  val RED, YELLOW, GREEN, BLUE = Value
}