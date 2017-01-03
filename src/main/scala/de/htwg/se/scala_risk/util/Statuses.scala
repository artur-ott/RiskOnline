package de.htwg.se.scala_risk.util

object Statuses extends Enumeration {
  val
  // Game specific statuses
  CREATE_GAME,
  INITIALIZE_PLAYERS,
  GAME_INITIALIZED,
  
  // Player statuses
  PLAYER_SPREED_TROOPS,
  PLAYER_ATTACK,
  PLAYER_MOVE_TROOPS,
  
  // Error statuses
  NOT_ENOUGH_PLAYERS
  
  = Value
}