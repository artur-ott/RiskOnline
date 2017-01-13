package de.htwg.se.scala_risk.controller

import de.htwg.se.scala_risk.util.observer.Obserable
import de.htwg.se.scala_risk.util.Statuses
import de.htwg.se.scala_risk.model.Country

trait GameLogic extends Obserable with PlayerController with CountryController {
  def getCountries: scala.collection.mutable.ArrayBuffer[(String, String, Int)]
  def startGame
  def initializeGame
  def getStatus: Statuses.Value
  def getRolledDieces: (List[Int], List[Int])
  def getAttackerDefenderIndex: (Int, Int)
  def endTurn
  def getCandidates(country: String): List[(String, String, Int)]
  def getCurrentPlayerColor() : String
  
  def setStatus(status: Statuses.Value)// TODO: REMOVE setStatus
}
