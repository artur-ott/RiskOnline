package de.htwg.se.scala_risk.model

trait Player {
  def ==(that: Player): Boolean
  def !=(that: Player): Boolean
}
