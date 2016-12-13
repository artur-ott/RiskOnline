package de.htwg.se.scala_risk.model.impl
import org.scalatest.WordSpec
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.impl.Colors._
import org.scalatest.Matchers

class PlayerSpec extends WordSpec with Matchers {
  "A Player" should {
    val player1 = Player("Peter", RED)
    "have a name" in {
      
      player1.name should be("Peter")
    }
    "have a color" in {
      player1.color should be(RED)
    }
  }
  "Two Players with the same name and the same color" should {
    "be equal" in {
      val player1 = Player("Hans", RED)
      val player2 = Player("Hans", RED)
      player1 == player2 shouldBe true
    }
  }
  "Two Players with the same name and different colors" should {
    "not be equal" in {
      val player1 = Player("Hans", BLUE)
      val player2 = Player("Hans", RED)
      player1 != player2 shouldBe true
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
      // Clean playerList and colorList.
      Players.playerList = List()
      Players.colorList = List(RED, YELLOW, GREEN, BLUE)
      
      Players.addPlayer("Hans", "RED")
      Players.addPlayer("Peter", "BLUE")
      Players.playerList.length should be(2)
      Players.playerList(0) == player1 shouldBe true
      Players.playerList(1) == player2 shouldBe true
    }
  } 
  
  "The colorList after adding two (not equal) players" should {
    "not contain their colors" in {
      Players.colorList.contains(RED) shouldBe false
      Players.colorList.contains(BLUE) shouldBe false
    }
    "still contain the other colors" in {
      Players.colorList.contains(YELLOW) shouldBe true
      Players.colorList.contains(GREEN) shouldBe true
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
      Players.playerList.contains(player1) shouldBe true
    }
    "not contain the second player" in {
      Players.playerList.contains(player1) shouldBe true
      Players.playerList.contains(player2) shouldBe false
      
    }
  }
  
  "The colorList after adding two player with the same color" should {
    "not contain this color anymore" in {
      Players.colorList.contains(GREEN) shouldBe false
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
      Players.playerList.length should be (2)
      Players.playerList.contains(player1) shouldBe true
      Players.playerList.contains(player3) shouldBe true
    }
    "not contain the other player with the same color" in {
      Players.playerList.contains(player2) shouldBe false
    }
  }
  
  "The colorList after adding three players (two with the same color)" should {
    "not contain the taken colors anymore" in {
      Players.colorList.contains(GREEN) shouldBe false
      Players.colorList.contains(YELLOW) shouldBe false
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
      Players.playerList.length should be (2)
      Players.playerList.contains(player1) shouldBe true
      Players.playerList.contains(player2) shouldBe true
    }
  }
  
  "The colorList after adding two players with different color spelling" should {
    "not contain these colors anymore" in {
      Players.colorList.contains(RED) shouldBe false
      Players.colorList.contains(YELLOW) shouldBe false
    }
  }

}