package de.htwg.se.scala_risk.model
import de.htwg.se.scala_risk.model.impl.Colors._

trait Player {
  def getName : String
  def getColor : Color
  def getOwnedCountries : List[Country]
}