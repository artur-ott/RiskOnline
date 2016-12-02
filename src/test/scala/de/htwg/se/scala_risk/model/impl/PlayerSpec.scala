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
  
}