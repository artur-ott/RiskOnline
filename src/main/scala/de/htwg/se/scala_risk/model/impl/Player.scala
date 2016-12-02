package de.htwg.se.scala_risk.model.impl

object Colors extends Enumeration {
  type Color = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._
import Players._


case class Player(name : String, color : Color) {
    def ==(that: Player): Boolean = 
      if (this.name.equals(that.name) && this.color.equals(that.color)) 
        true 
      else 
        false
        
    def !=(that: Player): Boolean = ! (this == that)
}


object Players {
  var playerList : List[Player] = List()
  var colorList : List[Color] = List(RED, YELLOW, GREEN, BLUE) 
  val Default = Player("", null.asInstanceOf[Color])
  def addPlayers(array : List[(String,Color)]) /*: String = */{
    for (tupel <- array) {
      if (colorList.contains(tupel._2)) {
        playerList ::: List(Player(tupel._1, tupel._2))
        colorList = colorList.filter { x => x != tupel._2 }
      } else {
        println("Color already taken!")
      }
    } 
    colorList.foreach { c => println(c) }
    playerList.foreach { p => println(p.name) }
    
  }
}

