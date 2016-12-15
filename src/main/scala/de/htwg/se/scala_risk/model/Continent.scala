package de.htwg.se.scala_risk.model

trait Continent {
  def checkOwner() 
  def getOwner() : Player
  def getName() : String
  def getBonusTroops() : Int
}