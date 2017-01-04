package de.htwg.se.scala_risk.util

object Statuses extends Enumeration {
  val
  // Game specific statuses
  CREATE_GAME,
  INITIALIZE_PLAYERS,
  GAME_INITIALIZED,
  
  // Player statuses
  PLAYER_SPREAD_TROOPS,
  PLAYER_ATTACK,
  PLAYER_MOVE_TROOPS,
  
  // Error statuses
  NOT_ENOUGH_PLAYERS,
  COUNTRY_NOT_FOUND,
  COUNTRY_DOES_NOT_BELONG_TO_PLAYER,
  NOT_ENOUGH_TROOPS_TO_SPREAD,
  PLAYER_ATTACKING_HIS_COUNTRY,
  NOT_ENOUGH_TROOPS_TO_ATTACK
  
  = Value
}