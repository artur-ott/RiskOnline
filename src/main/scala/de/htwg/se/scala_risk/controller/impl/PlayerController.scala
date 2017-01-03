package de.htwg.se.scala_risk.controller.impl

import de.htwg.se.scala_risk.controller.{PlayerController => TPlayerController}
import de.htwg.se.scala_risk.model.World.Players

trait PlayerController extends TPlayerController {
  def getAvailableColors: List[String] = Players.colorList.map { x => x.toString() }
  
  def setPlayer(player: (String, String)) = Players.addPlayer(player._1, player._2)
  
  def getCurrentPlayer:(String, String) = (Players.playerList(Players.currentPlayer).getName, 
      Players.playerList(Players.currentPlayer).getColor.toString())
}