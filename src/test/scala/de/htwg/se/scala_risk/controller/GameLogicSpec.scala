package de.htwg.se.scala_risk.model.impl

import org.scalatest.Matchers
import org.scalatest.WordSpec

import scala.collection._
import de.htwg.se.scala_risk.controller.impl._
import de.htwg.se.scala_risk.model.impl.World.Players

class GameLogicSpec extends WordSpec with Matchers {
  val gameLogic: GameLogic = new GameLogic

  "Created players \"Player1, Red\" and \"Player2, Blue\"" should {
    gameLogic.setPlayer(("Player1", "RED"))
    gameLogic.setPlayer(("Player2", "BLUE"))
    "be found in the player list" in {
      Players.playerList.length should be(2)
    }
  }

  "Random dice" should {
    "always be bigger than or equals 1" in {
      gameLogic.randomDice() should be >= 1
    }
    "and always be smaller than or equal 6" in {
      gameLogic.randomDice() should be <= 6
    }
  }
}