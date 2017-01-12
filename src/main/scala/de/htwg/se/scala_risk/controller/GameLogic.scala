package de.htwg.se.scala_risk.controller

import de.htwg.se.scala_risk.util.observer.Obserable
import de.htwg.se.scala_risk.util.Statuses

trait GameLogic extends Obserable with PlayerController with CountryController {
  def getCountries: scala.collection.mutable.ArrayBuffer[(String, String, Int)]
  def startGame
  def initializeGame
  def getStatus: Statuses.Value
  def getRolledDieces: (List[Int], List[Int])
  def getAttackerDefenderIndex: (Int, Int)
  def endTurn
}