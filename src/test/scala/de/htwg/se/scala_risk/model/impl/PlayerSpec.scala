package de.htwg.se.scala_risk.model.impl
import org.scalatest.WordSpec
import de.htwg.se.scala_risk.model.Players

import org.scalatest.Matchers

class PlayerSpec extends WordSpec with Matchers {
  "A Player" should {
    val player1 = Player("Peter", Colors.RED)
    "have a name" in {
      player1.name should be("Peter")
    }
    "have a color" in {
      player1.color should be(Colors.RED)
    }
  }
  "Two Players" should {
    val player2 = Player("Hans", Colors.RED)
    val player3 = Player("Hans", Colors.RED)
    "be equal" in {
      player2 == player3 shouldBe true
    }
  }
  "Two Players" should {
    val player4 = Player("Hans", Colors.BLUE)
    val player5 = Player("Hans", Colors.RED)
    "not be equal" in {
      player4 != player5 shouldBe true
    }
  }
  "The Default Player" should {
    "have no name" in {
      Players.Default.name should be("")
    }
    "have no color" in {
      Players.Default.color should be(null)
    }
  }

  "The player list after adding two (not equal) players" should {
    Players.addPlayers(List(("Hans", Colors.RED), ("Peter", Colors.BLUE)))
    "contain two players" in {
      Players.playerList.length should be (2)
    }
    "that are not equal" in {
      Players.playerList(0) != Players.playerList(1) shouldBe true
    }
  }

}