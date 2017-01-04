package de.htwg.se.scala_risk.controller.impl

import de.htwg.se.scala_risk.util.Statuses

import de.htwg.se.scala_risk.controller.{GameLogic => TGameLogic}
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.Country
import de.htwg.se.scala_risk.model.Player

class GameLogic extends TGameLogic {
  
  var status:Statuses.Value = Statuses.CREATE_GAME
  
  private[GameLogic] val INIT_TROOPS: Int = 3
  
  def startGame = {
    this.setStatus(Statuses.INITIALIZE_PLAYERS)
  }
  
  def initializeGame() = {
    val players = Players.playerList
    
    if (players.length >= 2) {
  		val countries = util.Random.shuffle(Countries.listCountries)
  
  		countries.foreach { x => {
  		    val country = Countries.listCountries.indexOf(x)
  		    Countries.listCountries(country).setTroops(INIT_TROOPS); 
  		    Countries.listCountries(country).setOwner(players(countries.indexOf(x) % players.length))
  		  } 
  		}
      Players.nextPlayer()
      this.setStatus(Statuses.GAME_INITIALIZED)
      logic
    } else {
      this.setStatus(Statuses.NOT_ENOUGH_PLAYERS)
    }
  }
  
  def logic() = {
    this.status match {
      case Statuses.GAME_INITIALIZED => this.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
      case Statuses.PLAYER_SPREAD_TROOPS => this.setStatus(Statuses.PLAYER_ATTACK) 
      case Statuses.PLAYER_ATTACK => this.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      case Statuses.PLAYER_MOVE_TROOPS => {
        val oldPlayer = Players.currentPlayer
        Players.nextPlayer()
        if (oldPlayer != -1 && Players.currentPlayer == 0) {
          // check game
        } else {
          this.troopsToSpread = 3
          this.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
        }
      }
    }
  }
  
  /*private[this]*/ def setStatus(status:Statuses.Value) = {
    this.status = status
    notifyObservers
  }
  
  def getStatus:Statuses.Value = this.status
  
  /* Country operations */
  def getCountries:scala.collection.mutable.ArrayBuffer[(String, String, Int)] = 
    Countries.listCountries.map { x => (x.name, x.owner.getName, x.troops) }
  
  def getCandidates(country: String = ""): List[(String, String, Int)] = {
    this.status match {
      case Statuses.PLAYER_SPREAD_TROOPS => {
        val list = Countries.listCountries.filter{x => x.getOwner.equals(Players.playerList(Players.currentPlayer)) }
        list.map{x => (x.getName, x.getOwner.getName, x.getTroops)}.toList
      }
      case Statuses.PLAYER_ATTACK => {
        val candidates = getNeighbours(country).filterNot { x => x.getOwner.equals(Players.playerList(Players.currentPlayer)) }
        candidates.map { x => (x.getName, x.getOwner.getName, x.getTroops) }
      }
      case Statuses.PLAYER_MOVE_TROOPS => {
        val candidates = getNeighbours(country).filter { x => x.getOwner.equals(Players.playerList(Players.currentPlayer)) }
        candidates.map { x => (x.getName, x.getOwner.getName, x.getTroops) }
      }
      case _ => Nil
    }
  }
  
  private def getNeighbours(country: String): List[Country] = {
    val index = Countries.listCountries.indexWhere { x => x.name.toUpperCase().equals(country.toUpperCase()) }
    if (index < 0) this.setStatus(Statuses.COUNTRY_NOT_FOUND); Nil
    Countries.listCountries(index).neighboring_countries.map { x => {
      val tempIndex = Countries.listCountries.indexWhere { e => x.getName.equals(e.name) }
      if (-1 < tempIndex) {
        Countries.listCountries(tempIndex)
      } else Nil
    } }.toList.asInstanceOf[List[Country]]
  }
  
  /* Player operations */
  var troopsToSpread = 3
  
  def getAvailableColors: List[String] = Players.colorList.map { x => x.toString() }
  
  def setPlayer(player: (String, String)) = Players.addPlayer(player._1, player._2)
  
  def getCurrentPlayer:(String, String) = (Players.playerList(Players.currentPlayer).getName, 
      Players.playerList(Players.currentPlayer).getColor.toString())
      
  def getTroopsToSpread:Int = this.troopsToSpread
  
  def addTroops(country: String, troops: Int) = {
    val foundCountries = Countries.listCountries.filter {
        x => x.name.toUpperCase().equals(country.toUpperCase())
      }
    if (foundCountries.length > 0) {
      val index = Countries.listCountries.indexOf(foundCountries(0))
      if (Countries.listCountries(index).owner.equals(Players.playerList(Players.currentPlayer))) {
        if (troops <= troopsToSpread) {
          Countries.listCountries(index).setTroops(Countries.listCountries(index).getTroops() + troops)
          troopsToSpread -= troops
          if (troopsToSpread == 0) logic
          else notifyObservers
        } else this.setStatus(Statuses.NOT_ENOUGH_TROOPS_TO_SPREAD)
      } else this.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
    } else this.setStatus(Statuses.COUNTRY_NOT_FOUND)
  }
  
  def attack(countryAttacker: String, countryDefender: String) {
    val indexAttacker = Countries.listCountries.indexWhere { x => x.name.toUpperCase().equals(countryAttacker.toUpperCase()) }
    val indexDefender = Countries.listCountries.indexWhere { x => x.name.toUpperCase().equals(countryDefender.toUpperCase()) }
    if (indexAttacker > -1 && indexDefender > -1) {
      if (Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer)) &&
          !Countries.listCountries(indexDefender).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        val dieces = this.rollDice(Countries.listCountries(indexAttacker), Countries.listCountries(indexDefender))
        
      } else if (Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer)) &&
          Countries.listCountries(indexDefender).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        this.setStatus(Statuses.PLAYER_ATTACKING_HIS_COUNTRY)
      } else if (!Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        this.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
      }
    } else this.setStatus(Statuses.COUNTRY_NOT_FOUND)
  }

  // Function to attack a country. TCountry is the trait type!
  def rollDice(attacker: Country, defender: Country): (List[Int], List[Int]) = {
    val troopsAttacker = attacker.getTroops
    val toopsDefender = defender.getTroops
    var dicesAttacker: List[Int] = null
    var dicesDefender: List[Int] = null
    if (troopsAttacker > 1) {
      troopsAttacker match {
        case 2 => dicesAttacker = List.fill(1)(randomDice())
        case 3 => dicesAttacker = List.fill(2)(randomDice())
        case _ => dicesAttacker = List.fill(3)(randomDice())
      }
      return (dicesAttacker, dicesDefender)
    } else {
      this.setStatus(Statuses.NOT_ENOUGH_TROOPS_TO_ATTACK)
      return (Nil, Nil)
    }
  }
  // Function to get dice values from 1 to 6
  def randomDice(): Int = ((Math.random() * 6) + 1).toInt
}