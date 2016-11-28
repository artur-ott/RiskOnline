package de.htwg.se.scala_risk.model.impl

object Colors extends Enumeration {
  type Colors = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._
import Players._
case class Player(name : String, color : Colors) {
    PlayerList = PlayerList ::: List(this)
    def ==(that: Player): Boolean = 
      if (this.name.equals(that.name) && this.color.equals(that.color)) 
        true 
      else 
        false
        
    def !=(that: Player): Boolean = ! (this == that)
}


object Players {
  var PlayerList : List[Player] = List()
  val Default = Player("", null.asInstanceOf[Colors])
}

