package de.htwg.se.scala_risk.model.impl

// Color enumeration with all legit colors.
object Colors extends Enumeration {
  type Color = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.{ Player => TPlayer }
import de.htwg.se.scala_risk.model.{ Country => TCountry }
// Class to create Player objects.
case class Player(name: String, color: Color) extends de.htwg.se.scala_risk.model.Player {
  // Checks if a player is equal to another player.
  override def equals(that : Any) : Boolean = {
    if (!that.isInstanceOf[TPlayer]) false
    var p = that.asInstanceOf[TPlayer]
    if (this.color == null && p.getColor == null && this.name.equals(p.getName)) return true
    if (this.name.equals(p.getName) && this.color.equals(p.getColor)) return true else return false
  }
  override def getOwnedCountries() : List[TCountry] = {
    var countriesOfPlayer: List[TCountry] = List()
    Countries.listCountries.foreach { x => if (x.getOwner == this) { countriesOfPlayer = x :: countriesOfPlayer }}
    return countriesOfPlayer
    
  }

  // Return the name of the player.
  override def getName(): String = return this.name
  override def getColor(): Color = return this.color
}
