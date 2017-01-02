package de.htwg.se.scala_risk.controller.impl
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries

import de.htwg.se.scala_risk.controller.{GameLogic => TGameLogic}
import de.htwg.se.scala_risk.model.Country
import de.htwg.se.scala_risk.model.Player
import de.htwg.se.scala_risk.controller.State

class GameLogic extends TGameLogic {
  var currentState: State = new StartState
  
  def initializeGame() = {
    currentState.use
    startGame
  }
  
  def startGame() = {
    currentState = new PlayerState
    nextPlayer()
    currentState.use
  }

  def setPlayers(players: List[(String, String)]) = players.foreach(p => Players.addPlayer(p._1, p._2))
  
  def nextPlayer() = {

        Players.nextPlayer()

        notifyObservers

  }
  
  def getCurrentPlayer():(String, String) = (Players.playerList(Players.currentPlayer).getName, 
      Players.playerList(Players.currentPlayer).getColor.toString())
      
  def getCountries():scala.collection.mutable.ArrayBuffer[(String, String, Int)] = 
    Countries.listCountries.map { x => (x.name, x.owner.getName, x.troops) }

  // Function to attack a country. TCountry is the trait type!
  def rollDice(attacker: Country, defender: Country): (List[Int], List[Int]) = {
    if (attacker.getNeighboringCountries.contains(defender)) {
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
      } else {
        println("You can only attack if you have more than 1 troop placed in this country!")
      }
      return (dicesAttacker, dicesDefender)
    } else {
      println("Country not a neighbor of your country!")
      return (Nil.asInstanceOf[List[Int]], Nil.asInstanceOf[List[Int]])
    }
  }
  // Function to get dice values fom 1 to 6
  def randomDice(): Int = ((Math.random() * 6) + 1).toInt
}