package de.htwg.se.scala_risk.model.impl
import de.htwg.se.scala_risk.model.World.Players

case class Country(name: String, var neighboring_countries: Set[Country] = Set.empty,
    var troops: Int = 0, var owner: Player = Players.Default) {
  def addNeighboringCountrie(country: Country) = this.neighboring_countries :: (country :: Nil)

  override def toString(): String = {
    var neighbors: String = ""
    neighboring_countries.foreach { c => neighbors += c.name + " " }
    "Name: " + name + ", Neigbors: " + neighbors + ", troops: " + troops + ", owner: " + owner.name
  }
}
