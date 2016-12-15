package de.htwg.se.scala_risk.model.impl
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.{ Player => TPlayer }

case class Country(name: String, val neighboring_countries: Set[TCountry] = Set.empty,
    val troops: Int = 0, val owner: TPlayer = Players.Default) extends de.htwg.se.scala_risk.model.Country {
  // Add neighboring countries to the country.
  def addNeighboringCountries(country: Country) = this.neighboring_countries :: (country :: Nil)

  override def getName(): String = return this.name
  override def getTroops(): Int = return this.troops
  override def setTroops(number: Int) = if (number >= 0) { Countries.setTroops(this, number) } else println("Invalid number!")
  override def getNeighboringCountries(): Set[TCountry] = neighboring_countries
  override def toString(): String = {
    var neighbors: String = ""
    neighboring_countries.foreach { c => neighbors += c.getName + " " }
    "Name: " + name + ", Neigbors: " + neighbors + ", troops: " + troops + ", owner: " + owner.getName  }
  override def getOwner(): TPlayer = owner
  override def setOwner(player : TPlayer) = {
    Countries.listCountries = Countries.listCountries.filter { x => x != this }
    Countries.listCountries = this.copy(owner = player) :: Countries.listCountries
  }
  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[TCountry]) return false
    var p = that.asInstanceOf[TCountry]
    if (this.name.equals(p.getName)) return true else return false
  }

  /* 
  // Function to attack a country. TCountry is the trait type!
  def attack(attacker : TCountry, defender : TCountry) : (List[Int], List[Int]) = {
    if (attacker.getNeighboringCountries.toSet.contains(defender)) println("hi")
    val troopsAttacker = attacker.getTroops
    val toopsDefender = defender.getTroops
    var dicesAttacker : List[Int] = null
    var dicesDefender : List[Int] = null
    if (troopsAttacker > 1) {
      troopsAttacker match {
      case 2 => dicesAttacker = List.fill(1)(randomDice())
      case 3 => dicesAttacker = List.fill(2)(randomDice())
      case _ => dicesAttacker = List.fill(3)(randomDice())
      }      
    } else {
      println("You can only attack if you have more than 1 troop placed in this country!")
    }
    return (dicesAttacker, dicesDefender)
  }
  
  def randomDice() : Int = ((Math.random() * 6) + 1).toInt
  */
}
