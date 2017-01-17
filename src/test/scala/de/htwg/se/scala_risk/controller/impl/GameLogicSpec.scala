package de.htwg.se.scala_risk.controller.impl

import org.scalatest.WordSpec
import org.scalatest.Matchers._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith


import scala.collection._
import scala.collection.mutable.ArrayBuffer
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.impl.World
import de.htwg.se.scala_risk.model.impl.Country
import de.htwg.se.scala_risk.util.Statuses

@RunWith(classOf[JUnitRunner])
class GameLogicSpec extends WordSpec {
  val world: World = new World
  val gameLogic: GameLogic = new GameLogic(world)

  "A country owner " should {
    "be the same" in {
      val countryList: ArrayBuffer[TCountry] = world.getCountriesList
      countryList(0).getOwner.getName should be(gameLogic.getOwnerName(countryList(0).getName))
    }
  }
  
  "Game logic" should {
    "have a start game" in {
      gameLogic.startGame
      gameLogic.getStatus should be (Statuses.INITIALIZE_PLAYERS)
    }
  }
  
  "Game logic have a initialize game" should {
    "with equals or less than 1 player status should be not enough players" in {
      gameLogic.initializeGame()
      gameLogic.getStatus should be (Statuses.INITIALIZE_PLAYERS)
    }
    "with more than 1 player status should be player spread troops" in {
      gameLogic.setPlayer(("Player1", "RED"))
      gameLogic.setPlayer(("Player2", "BLUE"))
      gameLogic.initializeGame()
      gameLogic.getStatus should be (Statuses.PLAYER_SPREAD_TROOPS)
    }
  }
  
  "A Game logic " should {
    "GAME_INITIALIZED" in {
      gameLogic.setStatus(Statuses.GAME_INITIALIZED)
      gameLogic.logic
      gameLogic.getStatus should be (Statuses.PLAYER_SPREAD_TROOPS)
    }
    "PLAYER_SPREAD_TROOPS" in {
      gameLogic.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
      gameLogic.logic
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "PLAYER_ATTACK" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      gameLogic.logic
      gameLogic.getStatus should be (Statuses.PLAYER_MOVE_TROOPS)
    }
    "PLAYER_MOVE_TROOPS" in {
      gameLogic.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      gameLogic.logic
      gameLogic.getStatus should be (Statuses.PLAYER_SPREAD_TROOPS)
    }
    "Other should stay the same" in {
      gameLogic.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
      gameLogic.logic
      gameLogic.getStatus should be (Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
    }
  }
  
  "Status" should {
    "be PLAYER_MOVE_TROOPS after end turn" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      gameLogic.endTurn
      gameLogic.getStatus should be (Statuses.PLAYER_MOVE_TROOPS)
    }
  }
  
  "Rolled dieces" should {
    "be a tuple as a list" in {
      gameLogic.rolledDieces = gameLogic.rollDice(new Country(null, world, troops=3), new Country(null, world, troops=3))
      gameLogic.getRolledDieces._1 should not be empty
      gameLogic.getRolledDieces._2 should not be empty
    }
  }
  
  "A game world" should {
    "contain 42 countries" in {
      gameLogic.getCountries.length should be(42)
    }
  }
  
  "getCandidates" should {
    "PLAYER_SPREAD_TROOPS" in {
      gameLogic.setStatus(Statuses.PLAYER_SPREAD_TROOPS)
      gameLogic.getCandidates().length should (be > 0)
    }
    "PLAYER_ATTACK" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      gameLogic.getCandidates(world.getCountriesList(0).getName).isInstanceOf[List[(String, String, Int)]] should be (true)
    }
    "PLAYER_MOVE_TROOPS" in {
      gameLogic.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      gameLogic.getCandidates(world.getCountriesList(0).getName).isInstanceOf[List[(String, String, Int)]] should be (true)
    }
    "COUNTRY_DOES_NOT_BELONG_TO_PLAYER" in {
      gameLogic.setStatus(Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER)
      gameLogic.getCandidates(world.getCountriesList(0).getName) should be (Nil)
    }
  }
  
  "getAttackerDefenderCountries" should {
    "null" in {
      gameLogic.getAttackerDefenderCountries should be (null)
    }
    "not null" in {
      gameLogic.attackerDefenderIndex = (0, 1)
      gameLogic.getAttackerDefenderCountries should not be (null)
    }
  }
  
  "getAttackerDefenderIndex" should {
    "null" in {
      gameLogic.getAttackerDefenderIndex should be ((0, 1))
    }
  }
  
  "getAvailableColors" should {
    "bla" in {
      gameLogic.getAvailableColors.length should be (4)
    }
  }
  
  "getCurrentPlayer" should {
    "bla" in {
      gameLogic.getCurrentPlayer should be (("Player2","BLUE"))
    }
  }
  
  "getTroopsToSpread" should {
    "bla" in {
      gameLogic.getTroopsToSpread should be > 1
    }
  }
  
  "addTroops" should {
    "not all troops spread" in {
      world.getCountriesList(0).setOwner(world.getPlayerList(world.getCurrentPlayerIndex))
      val troops = world.getCountriesList(0).getTroops + gameLogic.getTroopsToSpread - 1
      gameLogic.addTroops(world.getCountriesList(0).getName, gameLogic.getTroopsToSpread - 1)
      world.getCountriesList(0).getTroops should be (troops)
    }
    "all troops spread" in {
      world.getCountriesList(0).setOwner(world.getPlayerList(world.getCurrentPlayerIndex))
      val troops = world.getCountriesList(0).getTroops + gameLogic.getTroopsToSpread
      gameLogic.addTroops(world.getCountriesList(0).getName, gameLogic.getTroopsToSpread)
      world.getCountriesList(0).getTroops should be (troops)
    }
    "spread more troops" in {
      world.getCountriesList(0).setOwner(world.getPlayerList(world.getCurrentPlayerIndex))
      val troops = world.getCountriesList(0).getTroops
      gameLogic.addTroops(world.getCountriesList(0).getName, gameLogic.getTroopsToSpread + 1)
      world.getCountriesList(0).getTroops should be (troops)
    }
    "spread troops to not owned country" in {
      world.getCountriesList(0).setOwner(world.getPlayerList(if (world.getCurrentPlayerIndex == 0) 1 else 0))
      val troops = world.getCountriesList(0).getTroops
      gameLogic.addTroops(world.getCountriesList(0).getName, gameLogic.getTroopsToSpread)
      world.getCountriesList(0).getTroops should be (troops)
    }
    "spread troops to no country" in {
      val troops = gameLogic.getTroopsToSpread
      gameLogic.addTroops("no", gameLogic.getTroopsToSpread)
      gameLogic.getTroopsToSpread should be (troops)
    }
  }
  
  "attack" should { 
    "own country" in {
      world.getCountriesList(0).setOwner(world.getPlayerList(world.getCurrentPlayerIndex))
      val troops = world.getCountriesList(0).getTroops
      gameLogic.attack(world.getCountriesList(0).getName, world.getCountriesList(0).getName)
      world.getCountriesList(0).getTroops should be (troops)
    }
    "not neigbour" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      val troops = world.getCountriesList(0).getTroops
      val index = world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) && !world.getCountriesList(0).getNeighboringCountries.contains(x) }
      gameLogic.attack(world.getCountriesList(0).getName, world.getCountriesList(index).getName)
      world.getCountriesList(0).getTroops should be (troops)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "normal" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      val troops = world.getCountriesList(0).getTroops
      val index = world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) && world.getCountriesList(0).getNeighboringCountries.contains(x) }
      gameLogic.attack(world.getCountriesList(0).getName, world.getCountriesList(index).getName)
      (gameLogic.getStatus == Statuses.PLAYER_ATTACK || gameLogic.getStatus == Statuses.PLAYER_CONQUERED_A_COUNTRY) should be (true)
    }
  }
  
  "getExtantTroops" should  {
    "bla" in {
      val troopsAtt = 3
      val troopsDef = 3
      gameLogic.rolledDieces = (6::5::Nil, 5::5::Nil)
      gameLogic.getExtantTroops(troopsAtt, troopsDef, 2) should be ((2,2))
    }
  }
  
  "checkConquered" should  {
    "extantTroopsDefender = 0" in {
      val pIndex = world.getCurrentPlayerIndex
      gameLogic.attackerDefenderIndex = (pIndex, pIndex)
      gameLogic.checkConquered(0, "")
      gameLogic.getStatus should be (Statuses.PLAYER_CONQUERED_A_CONTINENT)
    }
    "extantTroopsDefender = 0 with a country" in {
      val pIndex = world.getCurrentPlayerIndex
      gameLogic.attackerDefenderIndex = (pIndex, pIndex)
      gameLogic.checkConquered(0, world.getCountriesList(0).getName)
      gameLogic.getStatus should be (Statuses.PLAYER_CONQUERED_A_COUNTRY)
    }
    "extantTroopsDefender = 1 with a country" in {
      val pIndex = world.getCurrentPlayerIndex
      gameLogic.attackerDefenderIndex = (pIndex, pIndex)
      gameLogic.checkConquered(1, world.getCountriesList(0).getName)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
  }
  
  "getAttackIndexes" should {
    "no countries found" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      world.getCountriesList(0).setOwner(world.getPlayerList(world.getCurrentPlayerIndex))
      gameLogic.getAttackIndexes("", "") should be ((-1, -1))
    }
    "countries found" in {
      val indexDef = world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) && world.getCountriesList(0).getNeighboringCountries.contains(x) }
      val index = gameLogic.getAttackIndexes(world.getCountriesList(0).getName, world.getCountriesList(indexDef).getName)
      index._1 should be > -1
      index._2 should be > -1
    }
    "player attacking his own country" in {
      gameLogic.getAttackIndexes(world.getCountriesList(0).getName, world.getCountriesList(0).getName)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "player attacking with country which doesn't belog to him" in {
      val index = world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) }
      gameLogic.getAttackIndexes(world.getCountriesList(index).getName, world.getCountriesList(index).getName)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
  }
  
  "moveTroops" should {
    "wrong status" in {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      gameLogic.moveTroops(0)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "wrong amount of troops" in {
      gameLogic.setStatus(Statuses.PLAYER_CONQUERED_A_COUNTRY)
      gameLogic.attackerDefenderIndex = (0, world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) && world.getCountriesList(0).getNeighboringCountries.contains(x) })
      gameLogic.moveTroops(0)
      gameLogic.getStatus should be (Statuses.PLAYER_CONQUERED_A_COUNTRY)
    }
    "move troops after PLAYER_CONQUERED_A_COUNTRY or PLAYER_CONQUERED_A_CONTINENT" in {
      gameLogic.setStatus(Statuses.PLAYER_CONQUERED_A_COUNTRY)
      gameLogic.attackerDefenderIndex = (0, world.getCountriesList.indexWhere { x => !x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1) && world.getCountriesList(0).getNeighboringCountries.contains(x) })
      gameLogic.moveTroops(1)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "move troops turn" in {
      gameLogic.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      gameLogic.attackerDefenderIndex = (0, world.getCountriesList.indexWhere { x => x.getOwner.getName.equals(gameLogic.getCurrentPlayer._1)})
      gameLogic.moveTroops(1)
      gameLogic.getStatus should be (Statuses.PLAYER_MOVE_TROOPS)
    }
  }
  
  "dragTroops" should {
    "wrong status" in  {
      gameLogic.setStatus(Statuses.PLAYER_ATTACK)
      gameLogic.dragTroops("", "", 0)
      gameLogic.getStatus should be (Statuses.PLAYER_ATTACK)
    }
    "wrong country" in  {
      gameLogic.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      gameLogic.dragTroops("", world.getCountriesList(0).getName, 0)
      gameLogic.getAttackerDefenderIndex should be (-1, -1)
    }
    "move troops" in  {
      gameLogic.setStatus(Statuses.PLAYER_MOVE_TROOPS)
      gameLogic.dragTroops(world.getCountriesList(0).getName, world.getCountriesList(0).getName, 0)
      gameLogic.getAttackerDefenderIndex should be (0, 0)
    }
  }

  "rollDice" should {
    "troops are < 2" in {
      world.getCountriesList(0).setTroops(1)
      gameLogic.rollDice(world.getCountriesList(0), world.getCountriesList(0)) should be ((Nil, Nil))
    }
    "attaker troops = 2, defender troops = 1" in {
      world.getCountriesList(0).setTroops(2)
      world.getCountriesList(1).setTroops(1)
      gameLogic.rollDice(world.getCountriesList(0), world.getCountriesList(1)) should not be ((Nil, Nil))
    }
    "attaker troops = 3, defender troops = 2" in {
      world.getCountriesList(0).setTroops(3)
      world.getCountriesList(1).setTroops(2)
      gameLogic.rollDice(world.getCountriesList(0), world.getCountriesList(1)) should not be ((Nil, Nil))
    }
    "attaker troops > 3, defender troops = 2" in {
      world.getCountriesList(0).setTroops(4)
      world.getCountriesList(1).setTroops(2)
      gameLogic.rollDice(world.getCountriesList(0), world.getCountriesList(1)) should not be ((Nil, Nil))
    }
  }

  "getCurrentPlayerColor" should {
    "current player should have the same color" in {
      gameLogic.getCurrentPlayerColor should be (world.getPlayerList(world.getCurrentPlayerIndex).getColor.toString())
    }
  }

  "Created players \"Player1, Red\" and \"Player2, Blue\"" should {
    "be found in the player list" in {
      world.getPlayerList.length should be(2)
    }
  }
  
  "getOwnerColor" should {
    "bla" in {
      gameLogic.setPlayer(("Player3", "YELLOW"))
      gameLogic.setPlayer(("Player4", "GREEN"))
      gameLogic.setPlayer(("Player5", "PINK"))
      gameLogic.setPlayer(("Player6", "ORANGE"))
      gameLogic.getOwnerColor("Player1") should be (-57312)
      gameLogic.getOwnerColor("Player2") should be (-8350209)
      gameLogic.getOwnerColor("Player3") should be (-5888)
      gameLogic.getOwnerColor("Player4") should be (-10420362)
      gameLogic.getOwnerColor("Player5") should be (-563473)
      gameLogic.getOwnerColor("Player6") should be (-355265)
    }
  }

  "Random dice" should {
    "always be between 1 and 6" in {
      gameLogic.randomDice() should (be >= 1 and be <= 6)
    }
  }

  "To xml" should {
    "bla" in {
      gameLogic.rolledDieces = (6::5::Nil, 5::5::Nil)
      gameLogic.attackerDefenderIndex = (1,2)
      gameLogic.troopsToSpread = 6
      print (gameLogic.toXml)
      gameLogic.fromXml(gameLogic.toXml)
      1 should be (1)
    }
  }
}