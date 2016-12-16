package de.htwg.se.scala_risk.model.impl
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.World.Continents
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.{ Player => TPlayer }

/**
 * Class to create country objects.
 * @author Nico Lutz
 */
case class Country(name: String, neighboring_countries: Set[TCountry] = Set.empty,
    troops: Int = 0, owner: TPlayer = Players.Default) extends de.htwg.se.scala_risk.model.Country {
  
  override def getName(): String = return this.name
  override def getTroops(): Int = return this.troops
  override def setTroops(number: Int) = if (number > 0) Countries.listCountries(Countries.listCountries.indexOf(this)) = this.copy(troops = number)
  override def getNeighboringCountries(): Set[TCountry] = neighboring_countries
  override def toString(): String = {
    var neighbors: String = ""
    neighboring_countries.foreach { c => neighbors += c.getName + " " }
    "Name: " + name + ", Neigbors: " + neighbors + ", troops: " + troops + ", owner: " + owner.getName
  }
  override def getOwner(): TPlayer = if (owner == null) return Players.Default else return owner
  override def setOwner(player: TPlayer) = Countries.listCountries(Countries.listCountries.indexOf(this)) = this.copy(owner = player)
  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[TCountry]) return false
    var p = that.asInstanceOf[TCountry]
    if (this.name.equals(p.getName)) return true else return false
  }
}