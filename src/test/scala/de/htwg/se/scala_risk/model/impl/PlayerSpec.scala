package de.htwg.se.scala_risk.model.impl
import org.scalatest.WordSpec

import org.scalatest.Matchers



class PlayerSpec extends WordSpec with Matchers {
  "A Player" should {
    val player1 = Player("Peter", Colors.RED)
    "have a name" in {
      player1.name should be ("Peter")
    }
  }
  "Two Players" should {
    val player2 = Player("Hans", Colors.RED)
    val player3 = Player("Hans", Colors.RED)
    "not be equal" in {
      player2 != player3 shouldBe false
    } 
    
  }
  
}