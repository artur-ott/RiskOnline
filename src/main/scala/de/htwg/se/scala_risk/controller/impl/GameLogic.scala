package de.htwg.se.scala_risk.controller.impl
import de.htwg.se.scala_risk.model.World.Players

import de.htwg.se.scala_risk.model.impl.Colors

class GameLogic {
  initialize()
  def initialize() = {

  }

  def setPlayers(players: List[(String, String)]) = {}
}

/*
  // Function to attack a country. TCountry is the trait type!
  def attack(attacker : TCountry, defender : TCountry) : (List[Int], List[Int]) = {
    if (attacker.getNeighboringCountries.contains(defender)) {
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
    } else {
      println("Country not a neighbor of your country!")
      return (Nil.asInstanceOf[List[Int]], Nil.asInstanceOf[List[Int]])
    }
  }
  // Function to get dice values fom 1 to 6
  def randomDice() : Int = ((Math.random() * 6) + 1).toInt
  */ 