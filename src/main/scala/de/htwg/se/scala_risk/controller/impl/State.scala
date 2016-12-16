package de.htwg.se.scala_risk.controller.impl
import de.htwg.se.scala_risk.controller.{ State => IState }
import de.htwg.se.scala_risk.model.{ Player => IPlayer }
import de.htwg.se.scala_risk.model.World.Players

//TODO: durch traits ersetzen
import de.htwg.se.scala_risk.model.impl.Player

object State {
  private[State] val START_STATE: StartState = new StartState
  private[State] val PLAYER_STATE: PlayerState = new PlayerState
  private[State] var currentState: IState = null
  private[State] var currentPlayer: Player = null

  currentState = START_STATE

  def getPlayer(): IPlayer = currentPlayer
  def getOpponents: List[IPlayer] = Players.playerList.filterNot { x => x == currentPlayer }
  def getCurrentState: IState = currentState
}