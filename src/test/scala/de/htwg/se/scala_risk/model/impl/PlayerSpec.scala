package de.htwg.se.scala_risk.model.impl
import org.scalatest.WordSpec
import de.htwg.se.scala_risk.model.impl.World
import de.htwg.se.scala_risk.model.impl.Colors._
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec {
  "A Player" should {
    val player1 = Player("Peter", RED)
    "have a name" in {
      player1.name should be("Peter")
      player1.getName() should be("Peter")
    }
    "have a color" in {
      player1.color should be(RED)
      player1.getColor() should be(RED)
    }
  }
  "Two Players with the same name and the same color" should {
    "be equal" in {
      val player1 = Player("Hans", RED)
      val player2 = Player("Hans", RED)
      player1 should be(player2)
    }
  }
  "Two Players with the same name and different colors" should {
    "not be equal" in {
      val player1 = Player("Hans", BLUE)
      val player2 = Player("Hans", RED)
      player1.equals(player2) should be (false)
      
    }
  }
  
  "Two players with different instances" should {
    val player1 = Player("Hans", BLUE)
    val player2 = "Hans"
    "not be equal" in {
    player1.equals(player2) should be (false)
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

  "The playerList after adding two (not equal) players" should {
    val player1 = Player("Hans", RED)
    val player2 = Player("Peter", BLUE)
    "contain two players" in {
      update()

      Players.addPlayer("Hans", "RED")
      Players.addPlayer("Peter", "BLUE")
      Players.playerList.length should be(2)
      Players.playerList(0) should be(player1)
      Players.playerList(1) should be(player2)
    }
  }

  "The colorList after adding two (not equal) players" should {
    "not contain their colors" in {
      Players.colorList.contains(RED) should be(false)
      Players.colorList.contains(BLUE) should be(false)
    }
    "still contain the other colors" in {
      Players.colorList.contains(YELLOW) should be(true)
      Players.colorList.contains(GREEN) should be(true)
    }
  }

  "The playerList after adding two players with the same color" should {
    val player1 = Player("Julia", GREEN)
    val player2 = Player("Anna", GREEN)
    "contain only the first player" in {
      update()

      Players.addPlayer("Julia", "GREEN")
      Players.addPlayer("Anna", "GREEN")
      Players.playerList.length should be(1)
      Players.playerList.contains(player1) should be(true)
    }
    "not contain the second player" in {
      Players.playerList.contains(player1) should be(true)
      Players.playerList.contains(player2) should be(false)

    }
  }

  "The colorList after adding two player with the same color" should {
    "not contain this color anymore" in {
      Players.colorList.contains(GREEN) should be(false)
    }
  }

  "The playerList after adding three players (two with the same color)" should {
    val player1 = Player("Julia", GREEN)
    val player2 = Player("Anna", GREEN)
    val player3 = Player("Christian", YELLOW)
    "contain only the players with different colors" in {
      update()

      Players.addPlayer("Julia", "GREEN")
      Players.addPlayer("Anna", "GREEN")
      Players.addPlayer("Christian", "YELLOW")
      Players.playerList.length should be(2)
      Players.playerList.contains(player1) should be(true)
      Players.playerList.contains(player3) should be(true)
    }
    "not contain the other player with the same color" in {
      Players.playerList.contains(player2) should be(false)
    }
  }

  "The colorList after adding three players (two with the same color)" should {
    "not contain the taken colors anymore" in {
      Players.colorList.contains(GREEN) should be(false)
      Players.colorList.contains(YELLOW) should be(false)
    }

  }

  "The playerList after adding two players with different color spelling" should {
    val player1 = Player("Marcel", RED)
    val player2 = Player("Josef", YELLOW)
    "accept both players" in {
      update()

      Players.addPlayer("Marcel", "red")
      Players.addPlayer("Josef", "yeLLoW")
      Players.playerList.length should be(2)
      Players.playerList.contains(player1) should be(true)
      Players.playerList.contains(player2) should be(true)
    }
  }

  "The colorList after adding two players with different color spelling" should {
    "not contain these colors anymore" in {
      Players.colorList.contains(RED) should be(false)
      Players.colorList.contains(YELLOW) should be(false)
    }
  }

  "The playerList after adding a player with a wrong color" should {
    "not contain this player" in {
      update()

      Players.addPlayer("Michael", "wrongColor!")
      Players.playerList.length should be(0)
    }
  }

  "The colorList after adding a player with a wrong color" should {
    "still contain all colors" in {
      Players.colorList.length should be(4)
    }
  }

  "A player after assigning some countries to him it" should {
    "return these countries when asked for his owned countries" in {
      update()

      Players.addPlayer("Carl", "GREEN")
      val player = Players.playerList(0)
      val idxcountry1 = 0
      val idxcountry2 = 1
      val idxcountry3 = 2
      val idxcountry4 = 4
      Countries.listCountries(idxcountry1).setOwner(player)
      Countries.listCountries(idxcountry2).setOwner(player)
      Countries.listCountries(idxcountry3).setOwner(player)
      Countries.listCountries(idxcountry4).setOwner(player)
      player.getOwnedCountries.contains(Countries.listCountries(idxcountry1)) should be(true)
      player.getOwnedCountries.contains(Countries.listCountries(idxcountry2)) should be(true)
      player.getOwnedCountries.contains(Countries.listCountries(idxcountry3)) should be(true)
      player.getOwnedCountries.contains(Countries.listCountries(idxcountry4)) should be(true)
      Countries.listCountries(idxcountry1).getOwner() should be(player)
      Countries.listCountries(idxcountry2).getOwner() should be(player)
      Countries.listCountries(idxcountry3).getOwner() should be(player)
      Countries.listCountries(idxcountry4).getOwner() should be(player)
    }
  }

  "After searching an existing player by a valid color (String) it" should {
    "return this player" in {
      update()

      Players.addPlayer("Artur", "BLUE")
      val returnPlayer = Players.getPlayerFromColorString("BLUE")
      returnPlayer should be(Players.playerList(0))
    }
  }

  "After searching an existing player by a valid color with mixed upper-/lowercases it" should {
    "return this player" in {
      update()
      Players.addPlayer("Sandra", "YELLOW")
      val returnPlayer = Players.getPlayerFromColorString("yElLOw")
      returnPlayer should be(Players.playerList(0))
    }
  }

  "After searching a player by an invalid or non existing color it" should {
    "return the Default Player" in {
      update()

      var returnPlayer = Players.getPlayerFromColorString("BLUE")
      returnPlayer should be(Players.Default)

      returnPlayer = Players.getPlayerFromColorString("wrongColor")
      returnPlayer should be(Players.Default)
    }
  }

  def update() = {
    // Clean playerList and colorList.
    Players.playerList = scala.collection.mutable.ArrayBuffer()
    Players.colorList = List(RED, YELLOW, GREEN, BLUE)
  }

  // TODO: Add tests for return values of the addPlayer function!

}