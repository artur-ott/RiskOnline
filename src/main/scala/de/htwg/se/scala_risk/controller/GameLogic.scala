package de.htwg.se.scala_risk.controller

import de.htwg.se.scala_risk.util.observer.Obserable

trait GameLogic extends Obserable {
  def getCurrentPlayer:(String, String)
  def getCountries:scala.collection.mutable.ArrayBuffer[(String, String, Int)]
  def setPlayers(players: List[(String, String)])
  def initializeGame
}