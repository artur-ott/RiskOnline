package de.htwg.se.scala_risk.controller.impl
import de.htwg.se.scala_risk.controller.{ State => IState }
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.World.Players

class StartState extends IState {
  
  val INIT_TROOPS: Int = 3
  
  def use() = {
    val players = Players.playerList
    
    if (players.length >= 2) {
  		val countries = util.Random.shuffle(Countries.listCountries)
  
  		countries.foreach { x => {
  		    val country = Countries.listCountries.indexOf(x)
  		    Countries.listCountries(country).setTroops(INIT_TROOPS); 
  		    Countries.listCountries(country).setOwner(players(countries.indexOf(x) % players.length))
  		  } 
  		}
    }
  }
}