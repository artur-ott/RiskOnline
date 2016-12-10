package de.htwg.se.scala_risk.model.impl

object Colors extends Enumeration {
  type Color = Value
  val RED, YELLOW, GREEN, BLUE = Value
}

import Colors._

case class Player(name: String, color: Color) {
  def ==(that: Player): Boolean =
    if (this.name.equals(that.name) && this.color.equals(that.color))
      true
    else
      false

  def !=(that: Player): Boolean = !(this == that)
}

