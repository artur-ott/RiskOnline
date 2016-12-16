package de.htwg.se.scala_risk.model.impl
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.{ Player => TPlayer }
import de.htwg.se.scala_risk.model.{ Continent => TContinent }
import de.htwg.se.scala_risk.model.World.Continents
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.World.Players
import scala.collection._

/**
 * Class to create continents.
 * @author Nico Lutz
 */
case class Continent(name: String, countries: immutable.Set[Int],
    bonusTroops: Int) extends de.htwg.se.scala_risk.model.Continent {

  override def getOwner(): TPlayer = {
    val ownerCandidate: TPlayer = this.getIncludedCountries.head.getOwner
    val allOwnedByOne: Boolean = this.getIncludedCountries.forall { x => x.getOwner == ownerCandidate }
    if (allOwnedByOne) ownerCandidate
    else Players.Default
  }

  override def getIncludedCountries(): immutable.Set[TCountry] = {
    var includedCountries: immutable.Set[TCountry] = immutable.Set()
    for (x <- this.countries) includedCountries += Countries.listCountries(x)
    return includedCountries
  }
  override def getName(): String = return this.name
  override def getBonusTroops(): Int = return this.bonusTroops
  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[TContinent]) return false
    var p = that.asInstanceOf[TContinent]
    if (this.name.equals(p.getName)) return true else return false
  }
  override def toString(): String = {
    var containingCountries = ""
    this.getIncludedCountries().foreach { x => containingCountries += x.getName + " " }
    "Name: " + name + ", containing countries: " + containingCountries + ", bonus troops: " + bonusTroops + ", owner: " + this.getOwner()
  }

}