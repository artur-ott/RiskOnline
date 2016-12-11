package de.htwg.se.scala_risk.controller.impl
import de.htwg.se.scala_risk.model.Players
import de.htwg.se.scala_risk.model.impl.Colors

class GameLogic {
  initialize()
  def initialize() = {
    
  }
  
  def setPlayers(players:List[(String, String)]) = {
    Players.addPlayers(players.map(e => {
      if (Colors.values.exists { _.toString() == e._2 }) {
        (e._1, Colors.withName(e._2))
      } else
        ("", null)
    }))
  }
}