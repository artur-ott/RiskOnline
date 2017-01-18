package de.htwg.se.scala_risk.util

object Statuses extends Enumeration {
  val
  // Game specific statuses
  CREATE_GAME,
  INITIALIZE_PLAYERS,
  GAME_INITIALIZED,
  DIECES_ROLLED,
  
  // Player statuses
  PLAYER_SPREAD_TROOPS,
  PLAYER_ATTACK,
  PLAYER_MOVE_TROOPS,
  PLAYER_CONQUERED_A_COUNTRY,
  PLAYER_CONQUERED_A_CONTINENT,
  
  // Error statuses
  NOT_ENOUGH_PLAYERS,
  COUNTRY_NOT_FOUND,
  COUNTRY_DOES_NOT_BELONG_TO_PLAYER,
  NOT_ENOUGH_TROOPS_TO_SPREAD,
  PLAYER_ATTACKING_HIS_COUNTRY,
  NOT_ENOUGH_TROOPS_TO_ATTACK,
  INVALID_QUANTITY_OF_TROOPS_TO_MOVE,
  NOT_A_NEIGHBORING_COUNTRY
  
  
  = Value
  type Statuses = Value
  
  def toXml : String = {
    this.toString()
  }
}