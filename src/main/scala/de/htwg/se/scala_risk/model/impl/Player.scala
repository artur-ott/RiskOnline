package de.htwg.se.scala_risk.model.impl

// Color enumeration with all legit colors.
object Colors extends Enumeration {
  type Color = Value
  val RED, YELLOW, GREEN, BLUE, PINK, ORANGE = Value
}

import Colors._
import de.htwg.se.scala_risk.model.{World => TWorld}
import de.htwg.se.scala_risk.model.{ Player => TPlayer }
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import scala.collection._

/**
 * Class to create Player objects.
 * @author Nico Lutz
 */
case class Player(name: String, color: Color, world: TWorld) extends de.htwg.se.scala_risk.model.Player {
  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[TPlayer]) false
    var p = that.asInstanceOf[TPlayer]
    if (this.color == null && p.getColor == null && this.name.equals(p.getName)) return true
    if (this.name.equals(p.getName) && this.color.equals(p.getColor)) return true else return false
  }
  override def getOwnedCountries(): immutable.Set[TCountry] = {
    var countriesOfPlayer: immutable.Set[TCountry] = immutable.Set()
    world.getCountriesList.foreach { x => if (x.getOwner == this) { countriesOfPlayer += x } }
    return countriesOfPlayer

  }
  override def getName(): String = return this.name
  override def getColor(): Color = return this.color

}
