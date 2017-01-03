package de.htwg.se.scala_risk.controller

trait PlayerController {
  def getAvailableColors: List[String]
  def setPlayer(player: (String, String))
  def getCurrentPlayer:(String, String)
}