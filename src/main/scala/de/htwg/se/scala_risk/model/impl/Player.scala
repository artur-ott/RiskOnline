package de.htwg.se.scala_risk.model.impl

// Color enumeration with all legit colors.
object Colors extends Enumeration {
  type Color = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._
// Class to create Player objects.
case class Player(name: String, color: Color) extends de.htwg.se.scala_risk.model.Player {
  // Checks if a player is equal to another player.
  def ==(that: Player): Boolean =
    if (this.name.equals(that.name) && this.color.equals(that.color))
      true
    else
      false

  // Checks if a player is not equal to another player.
  def !=(that: Player): Boolean = !(this == that)

  // Return the name of the player.
  def getName(): String = name
}
