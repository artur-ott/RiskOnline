package de.htwg.se.scala_risk.model.impl

object Colors extends Enumeration {
  type Colors = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._
import Players._
case class Player(name : String, color : Colors) {
    PlayerList = PlayerList ::: List(this)
}


object Players {
  var PlayerList : List[Player] = List()
  val Player1 = Player("Jaromir", Colors.RED)
  val Player2 = Player("Jamal", Colors.BLUE)
}

