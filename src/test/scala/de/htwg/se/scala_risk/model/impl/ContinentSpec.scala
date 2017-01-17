//package de.htwg.se.scala_risk.model.impl
//
//import org.scalatest.Matchers
//import org.scalatest.WordSpec
//
//import de.htwg.se.scala_risk.model.impl.World.Continents
//import de.htwg.se.scala_risk.model.impl.World.Countries
//import de.htwg.se.scala_risk.model.impl.World.Players
//import de.htwg.se.scala_risk.model.impl.Colors._
//
//import scala.collection._
//
//class ContinentSpec extends WordSpec with Matchers {
//
//  val afrika = Continents.listContinents(1)
//
//  "listContinents" should {
//    "contain 2 continents" in {
//      Continents.listContinents.length should be(2)
//    }
//    "contain AFRIKA" in {
//      Continents.listContinents.contains(Continents.listContinents(1))
//    }
//  }
//
//  "The continent AFRIKA" should {
//    "have a name" in {
//      afrika.getName() should be("AFRIKA")
//    }
//    "have 3 bonusTroops" in {
//      afrika.getBonusTroops() should be(3)
//    }
//  }
//
//  "After one player owns all countries of AFRIKA it" should {
//    "be owned by this player" in {
//      Players.addPlayer("Jasmine", "RED")
//      afrika.getIncludedCountries.foreach { x => x.setOwner(Players.playerList(0)) }
//      afrika.getOwner() should be(Players.playerList(0))
//    }
//  }
//
//  "After a player owning a continent loses one country of it,it" should {
//    "not be the owner of the continent anymore" in {
//      update()
//
//      Players.addPlayer("Rick", "YELLOW")
//      Players.addPlayer("Rebecca", "BLUE")
//      afrika.getIncludedCountries.foreach { x => x.setOwner(Players.playerList(0)) }
//      afrika.getOwner() should be(Players.playerList(0))
//
//      afrika.getIncludedCountries().head.setOwner(Players.playerList(1))
//      afrika.getOwner() should be(Players.Default)
//    }
//  }
//
//  def update() = {
//    // Clean playerList and colorList.
//    Players.playerList = scala.collection.mutable.ArrayBuffer()
//    Players.colorList = List(RED, YELLOW, GREEN, BLUE)
//  }
//}