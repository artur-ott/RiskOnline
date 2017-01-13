package de.htwg.se.scala_risk.controller.impl

import de.htwg.se.scala_risk.util.Statuses

import de.htwg.se.scala_risk.controller.{ GameLogic => TGameLogic }
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.model.Country
import de.htwg.se.scala_risk.model.Player


class GameLogic extends TGameLogic {

  private[this] var status: Statuses.Value = Statuses.CREATE_GAME

  private[this] val INIT_TROOPS: Int = 3

  private[this] var attackerDefenderIndex = (-1, -1)
  private[this] var rolledDieces: (List[Int], List[Int]) = (Nil, Nil)

  def startGame = {
    this.setStatus(Statuses.INITIALIZE_PLAYERS)
  }

  def initializeGame() = {
    val players = Players.playerList

    if (players.length >= 2) {
      val countries = Countries.listCountries // TODO: Remove comment: util.Random.shuffle(Countries.listCountries)

      countries.foreach { x => {
          x.setTroops(INIT_TROOPS)
          x.setOwner(players(countries.indexOf(x) % players.length))
        }
      }
      Players.nextPlayer()
      this.setStatus(Statuses.GAME_INITIALIZED)
      logic
    } else {
      this.setStatus(Statuses.NOT_ENOUGH_PLAYERS)
    }
  }

  def logic = {
    this.status match {
      case Statuses.GAME_INITIALIZED => this.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
      case Statuses.PLAYER_SPREAD_TROOPS => this.setStatus(Statuses.PLAYER_ATTACK)
      case Statuses.PLAYER_ATTACK => this.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      case Statuses.PLAYER_MOVE_TROOPS => {
        val oldPlayer = Players.currentPlayer
        Players.nextPlayer()
        if (oldPlayer != -1 && Players.currentPlayer == 0) {
          this.checkGame
        } else {
          this.troopsToSpread = 3
          this.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
        }
      }
    }
  }
  
  private[this] def checkGame = {
    Players.playerList.foreach {
      e => {
        if (Countries.listCountries.exists { x => x.owner == e }) {
          
        } else {
          
        }
      }
    }
  }
  
  def endTurn = {
    this.logic
  }

  /*private[this]*/ def setStatus(status: Statuses.Value) = {
    this.status = status
    notifyObservers
  }

  def getStatus: Statuses.Value = this.status

  def getRolledDieces: (List[Int], List[Int]) = this.rolledDieces

  /* Country operations */
  def getCountries: scala.collection.mutable.ArrayBuffer[(String, String, Int)] =
    Countries.listCountries.map { x => (x.name, x.owner.getName, x.troops) }

  def getCandidates(country: String = ""): List[(String, String, Int)] = {
    this.status match {
      case Statuses.PLAYER_SPREAD_TROOPS => {
        val list = Countries.listCountries.filter { x => x.getOwner.equals(Players.playerList(Players.currentPlayer)) }
        list.map { x => (x.getName, x.getOwner.getName, x.getTroops) }.toList
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
    val index = this.getCountryIndexByString(country)
    if (index < 0) this.setStatus(Statuses.COUNTRY_NOT_FOUND); Nil
    Countries.listCountries(index).neighboring_countries.toList
  }
  
  def getAttackerDefenderIndex: (Int, Int) = this.attackerDefenderIndex

  /* Player operations */
  var troopsToSpread = 3

  def getAvailableColors: List[String] = Players.colorList.map { x => x.toString() }

  def setPlayer(player: (String, String)) = {
    Players.addPlayer(player._1, player._2)
    notifyObservers
  }

  def getCurrentPlayer: (String, String) = (
    Players.playerList(Players.currentPlayer).getName,
    Players.playerList(Players.currentPlayer).getColor.toString()
  )

  def getTroopsToSpread: Int = this.troopsToSpread

  def addTroops(country: String, troops: Int) = {
    val index = this.getCountryIndexByString(country)
    if (index >= 0) {
      if (Countries.listCountries(index).owner.equals(Players.playerList(Players.currentPlayer))) {
        if (troops <= troopsToSpread) {
          Countries.listCountries(index).setTroops(Countries.listCountries(index).getTroops() + troops)
          troopsToSpread -= troops
          if (troopsToSpread == 0) logic
          else notifyObservers
        } else {
          this.setStatus(Statuses.NOT_ENOUGH_TROOPS_TO_SPREAD)
          this.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
        }
      } else this.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
    } else this.setStatus(Statuses.COUNTRY_NOT_FOUND)
  }

  def attack(countryAttacker: String, countryDefender: String) = {
    if (this.status == Statuses.PLAYER_ATTACK) {
      this.attackerDefenderIndex = getAttackIndexes(countryAttacker, countryDefender);
      if (attackerDefenderIndex._1 != -1) {
        this.rolledDieces = this.rollDice(
          Countries.listCountries(attackerDefenderIndex._1),
          Countries.listCountries(attackerDefenderIndex._2)
        )
        this.setStatus(Statuses.DIECES_ROLLED)
        val min = Math.min(this.rolledDieces._1.length, this.rolledDieces._2.length)
        var extantTroopsAttacker = Countries.listCountries(attackerDefenderIndex._1).getTroops
        var extantTroopsDefender = Countries.listCountries(attackerDefenderIndex._2).getTroops
        var i = 0
        for (i <- 0 to min - 1) {
          if (this.rolledDieces._1(i) > this.rolledDieces._2(i)) {
            extantTroopsDefender -= 1
          } else {
            extantTroopsAttacker -= 1
          }
        }
        Countries.listCountries(attackerDefenderIndex._1).setTroops(extantTroopsAttacker)
        Countries.listCountries(attackerDefenderIndex._2).setTroops(extantTroopsDefender)
        if (extantTroopsDefender == 0) {
          Countries.listCountries(attackerDefenderIndex._2).setOwner(Countries.listCountries(attackerDefenderIndex._1).getOwner())
          this.setStatus(Statuses.PLAYER_CONQUERED_A_COUNTRY)
        } else {
          this.clearAttack
          this.setStatus(Statuses.PLAYER_ATTACK)
        }
      }
    }
  }

  private[this] def getAttackIndexes(countryAttacker: String, countryDefender: String): (Int, Int) = {
    val indexAttacker = this.getCountryIndexByString(countryAttacker)
    val indexDefender = this.getCountryIndexByString(countryDefender)
    if (indexAttacker > -1 && indexDefender > -1) {
      if (Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer)) &&
        !Countries.listCountries(indexDefender).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        return (indexAttacker, indexDefender)
      } else if (Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer)) &&
        Countries.listCountries(indexDefender).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        this.setStatus(Statuses.PLAYER_ATTACKING_HIS_COUNTRY)
      } else if (!Countries.listCountries(indexAttacker).getOwner().equals(Players.playerList(Players.currentPlayer))) {
        this.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
      }
    } else this.setStatus(Statuses.COUNTRY_NOT_FOUND)
    (-1, -1)
  }

  private[this] def clearAttack = {
    this.attackerDefenderIndex = (-1, -1)
    this.rolledDieces = (Nil, Nil)
  }

  def moveTroops(count: Int) = {
    if (this.status == Statuses.PLAYER_CONQUERED_A_COUNTRY || this.status == Statuses.PLAYER_MOVE_TROOPS) {
      val currentTroops = Countries.listCountries(this.attackerDefenderIndex._1).getTroops
      if (count < 1 || count >= currentTroops)
        this.setStatus(Statuses.INVALID_QUANTITY_OF_TROOPS_TO_MOVE)
      else if(this.status == Statuses.PLAYER_CONQUERED_A_COUNTRY) {
        Countries.listCountries(this.attackerDefenderIndex._1).setTroops(currentTroops - count)
        Countries.listCountries(this.attackerDefenderIndex._2).setTroops(count)
        this.clearAttack
        this.setStatus(Statuses.PLAYER_ATTACK)
      } else {
        Countries.listCountries(this.attackerDefenderIndex._1).setTroops(currentTroops - count)
        Countries.listCountries(this.attackerDefenderIndex._2).setTroops(count)
        this.clearAttack
        this.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      }
    }
  }
    
  private[this] def getCountryIndexByString(country: String) : Int = Countries.listCountries.indexWhere { x => x.name.toUpperCase().equals(country.toUpperCase())}
  
  
  def dragTroops(countryFrom: String, countryTo: String, troops: Int) = {
    if (this.status == Statuses.PLAYER_MOVE_TROOPS) {
      this.attackerDefenderIndex = (this.getCountryIndexByString(countryFrom), this.getCountryIndexByString(countryFrom))
      if (this.attackerDefenderIndex._1 < 0 || this.attackerDefenderIndex._2 < 0) {
        this.clearAttack
        this.setStatus(Statuses.COUNTRY_NOT_FOUND)
      } else {
        this.moveTroops(troops)
      }
    }
  }

  // Function to attack a country. TCountry is the trait type!
  def rollDice(attacker: Country, defender: Country): (List[Int], List[Int]) = {
    val troopsAttacker = attacker.getTroops
    val toopsDefender = defender.getTroops
    var dicesAttacker: List[Int] = Nil
    var dicesDefender: List[Int] = Nil
    // TODO: for testing remove comment return (6 :: 6 :: 6 :: Nil, 5 :: 5 :: 5 :: Nil)
    if (troopsAttacker > 1) {
      troopsAttacker match {
        case 2 => dicesAttacker = List.fill(1)(randomDice())
        case 3 => dicesAttacker = List.fill(2)(randomDice())
        case _ => dicesAttacker = List.fill(3)(randomDice())
      }
      toopsDefender match {
        case 1 => dicesDefender = List.fill(1)(randomDice())
        case 2 => dicesDefender = List.fill(2)(randomDice())
        case _ => dicesDefender = List.fill(3)(randomDice())
      }
      return (dicesAttacker.sortWith(_ > _), dicesDefender.sortWith(_ > _))
    } else {
      this.setStatus(Statuses.NOT_ENOUGH_TROOPS_TO_ATTACK)
      return (Nil, Nil)
    }
  }
  // Function to get dice values from 1 to 6
  def randomDice(): Int = ((Math.random() * 6) + 1).toInt
  
  
  def getCurrentPlayerColor() : String = {
    return Players.playerList(Players.currentPlayer).getColor.toString()
  }
  
}