package de.htwg.se.scala_risk.model

trait Country {
  def getName : String
  def getNeighboringCountries : Set[Country]
  def getTroops : Int
  def setTroops(number: Int)
  def getOwner : Player
  def setOwner(player : Player)
}