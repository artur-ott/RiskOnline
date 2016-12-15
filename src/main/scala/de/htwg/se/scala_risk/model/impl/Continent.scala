package de.htwg.se.scala_risk.model.impl
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.{ Player => TPlayer}
import de.htwg.se.scala_risk.model.World.Continents
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.World.Players
case class Continent(name: String, countries: Set[TCountry],
                     val owner: TPlayer, bonusTroups: Int) extends de.htwg.se.scala_risk.model.Continent {
  override def checkOwner() = {
    val ownerCandidate = this.countries.head.getOwner
    val allOwnedByOne : Boolean = this.countries.forall { x => x == ownerCandidate }
    Continents.listContinents.filter { x => x != this }
    if (allOwnedByOne) {
      Continents.listContinents = this.copy(owner = ownerCandidate) :: Continents.listContinents      
    } else {
      Continents.listContinents = this.copy(owner = Players.Default) :: Continents.listContinents
    }

  }
  override def getOwner() : TPlayer = return this.owner
  override def getName() : String = return this.name
  override def getBonusTroops() : Int = return this.bonusTroups
}