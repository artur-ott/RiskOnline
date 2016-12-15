package de.htwg.se.scala_risk.model.impl
import org.scalatest.WordSpec
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.impl.Colors._
import de.htwg.se.scala_risk.model.World.Countries
import org.scalatest.Matchers

class PlayerSpec extends WordSpec with Matchers {
  "A Player" should {
    val player1 = Player("Peter", RED)
    "have a name" in {

      player1.name should be("Peter")
      player1.getName() should be ("Peter")
    }
    "have a color" in {
      player1.color should be(RED)
      player1.getColor() should be (RED)
    }
  }
  "Two Players with the same name and the same color" should {
    "be equal" in {
      val player1 = Player("Hans", RED)
      val player2 = Player("Hans", RED)
      player1 should be (player2)
    }
  }
  "Two Players with the same name and different colors" should {
    "not be equal" in {
      val player1 = Player("Hans", BLUE)
      val player2 = Player("Hans", RED)
      player1 != player2 should be (true)
    }
  }
  "The Default Player" should {
    "have no name" in {
      Players.Default.name should be("")
    }
    "have no color" in {
      Players.Default.color should be (null)
    }
  }

  "The playerList after adding two (not equal) players" should {
    val player1 = Player("Hans", RED)
    val player2 = Player("Peter", BLUE)
    "contain two players" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)
      
      Players.addPlayer("Hans", "RED")
      Players.addPlayer("Peter", "BLUE")
      println("_____________________" + Players.playerList)
      Players.playerList.length should be(2)
      Players.playerList(1) should be (player1)
      Players.playerList(0) should be (player2)
    }
  }

  "The colorList after adding two (not equal) players" should {
    "not contain their colors" in {
      Players.colorList.contains(RED) should be (false)
      Players.colorList.contains(BLUE) should be (false)
    }
    "still contain the other colors" in {
      Players.colorList.contains(YELLOW) should be (true)
      Players.colorList.contains(GREEN) should be (true)
    }
  }

  "The playerList after adding two players with the same color" should {
    val player1 = Player("Julia", GREEN)
    val player2 = Player("Anna", GREEN)
    "contain only the first player" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)

      Players.addPlayer("Julia", "GREEN")
      Players.addPlayer("Anna", "GREEN")
      Players.playerList.length should be(1)
      Players.playerList.contains(player1) should be (true)
    }
    "not contain the second player" in {
      Players.playerList.contains(player1) should be (true)
      Players.playerList.contains(player2) should be (false)

    }
  }

  "The colorList after adding two player with the same color" should {
    "not contain this color anymore" in {
      Players.colorList.contains(GREEN) should be (false)
    }
  }

  "The playerList after adding three players (two with the same color)" should {
    val player1 = Player("Julia", GREEN)
    val player2 = Player("Anna", GREEN)
    val player3 = Player("Christian", YELLOW)
    "contain only the players with different colors" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)

      Players.addPlayer("Julia", "GREEN")
      Players.addPlayer("Anna", "GREEN")
      Players.addPlayer("Christian", "YELLOW")
      Players.playerList.length should be(2)
      Players.playerList.contains(player1) should be (true)
      Players.playerList.contains(player3) should be (true)
    }
    "not contain the other player with the same color" in {
      Players.playerList.contains(player2) should be (false)
    }
  }

  "The colorList after adding three players (two with the same color)" should {
    "not contain the taken colors anymore" in {
      Players.colorList.contains(GREEN) should be (false)
      Players.colorList.contains(YELLOW) should be (false)
    }

  }

  "The playerList after adding two players with different color spelling" should {
    val player1 = Player("Marcel", RED)
    val player2 = Player("Josef", YELLOW)
    "accept both players" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)

      Players.addPlayer("Marcel", "red")
      Players.addPlayer("Josef", "yeLLoW")
      Players.playerList.length should be(2)
      Players.playerList.contains(player1) should be (true)
      Players.playerList.contains(player2) should be (true)
    }
  }

  "The colorList after adding two players with different color spelling" should {
    "not contain these colors anymore" in {
      Players.colorList.contains(RED) should be (false)
      Players.colorList.contains(YELLOW) should be (false)
    }
  }

  "The playerList after adding a player with a wrong color" should {
    "not contain this player" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)

      Players.addPlayer("Michael", "wrongColor!")
      Players.playerList.length should be (0)
    }
  }

  "The colorList after adding a player with a wrong color" should {
    "still contain all colors" in {
      Players.colorList.length should be (4)
    }
  }
  
  "A player after assigning some countries to him it" should {
    "return these countries when asked for his owned countries" in {
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)
      Players.addPlayer("Carl", "GREEN")
      val player = Players.playerList(0)
      val country1 = Countries.listCountries(0)
      val country2 = Countries.listCountries(1)
      val country3 = Countries.listCountries(2)
      val country4 = Countries.listCountries(4)
      
      country1.setOwner(player)
      country2.setOwner(player)
      country3.setOwner(player)
      country4.setOwner(player)
      player.getOwnedCountries().contains(country1) should be (true)
      player.getOwnedCountries().contains(country2) should be (true)
      player.getOwnedCountries().contains(country3) should be (true)
      player.getOwnedCountries().contains(country4) should be (true)
    }
  }
  // todo: Add tests for return values of the addPlayer function!

}