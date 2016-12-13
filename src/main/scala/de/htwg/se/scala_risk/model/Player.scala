package de.htwg.se.scala_risk.model
import de.htwg.se.scala_risk.model.impl.{ Player => ImpPlayer }

trait Player {
  def ==(that: ImpPlayer): Boolean
  def !=(that: ImpPlayer): Boolean
  def getName: String
}