package de.htwg.se.scala_risk.view

import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.util.observer.TObserver
import de.htwg.se.scala_risk.util.Statuses
import de.htwg.se.scala_risk.model.World.Countries

class TUI(gameLogic: GameLogic) extends TObserver {
  val LENGTH = 30

  gameLogic.add(this)
  if (gameLogic.getStatus == Statuses.CREATE_GAME) {
    println("\n_______________________________________________________________________\n");
    println("\n______________________To start the game press s________________________\n");
    println("\n_______________________________________________________________________\n");
  }

  def setNextInput(input: String): Boolean = {
    gameLogic.getStatus match {
      case Statuses.CREATE_GAME => if (input.equals("s")) gameLogic.startGame
      case Statuses.INITIALIZE_PLAYERS => this.parsePlayer(input)
      case Statuses.GAME_INITIALIZED =>
    }
    if (!input.equals("q"))
      return true
    return false
  }

  def parsePlayer(player: String) {
    if (player.equals("v")) {
      gameLogic.initializeGame
    } else {
      val playerData = player.split(", ")
      if (playerData.length != 2) {
        if (playerData.length > 2)
          gameLogic.setPlayer((playerData(0), playerData(1)))
        else
          gameLogic.setPlayer(("", ""))
      }
    }
  }

  def update() = printTui

  private def printTui = {
    gameLogic.getStatus match {
      case Statuses.INITIALIZE_PLAYERS => this.printPlayerInitialisation
      case Statuses.GAME_INITIALIZED => printPitch
      case Statuses.PLAYER_SPREAD_TROOPS => printSpreadTroops
      case Statuses.PLAYER_ATTACK =>
      case Statuses.PLAYER_MOVE_TROOPS =>
      case Statuses.DIECES_ROLLED => printRolledDieces
      case Statuses.PLAYER_CONQUERED_A_COUNTRY =>

      // Errors
      case Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER => println("COUNTRY_DOES_NOT_BELONG_TO_PLAYER")
      case Statuses.NOT_ENOUGH_TROOPS_TO_SPREAD => println("NOT_ENOUGH_TROOPS_TO_SPREAD")
      case Statuses.COUNTRY_NOT_FOUND => println("COUNTRY_NOT_FOUND")
    }
  }

  def printRolledDieces = {
    println("-----------------------------------------------------------------------");
    val dieces = gameLogic.getRolledDieces
    val max = Math.max(dieces._1.length, dieces._2.length)
    val attackerDefenderIndex = gameLogic.getAttackerDefenderIndex
    val attackerName = Countries.listCountries(attackerDefenderIndex._1).getOwner.getName
    val defenderName = Countries.listCountries(attackerDefenderIndex._2).getOwner.getName
    val maxSpace = attackerName.length + 2
    val text: String = "%s%" + maxSpace + "s"
    println(text.format(attackerName, defenderName))
    var i = 0
    for (i <- 0 to max - 1) {
      var text1 = ""
      var text2 = ""
      if (dieces._1.length > i) text1 = dieces._1(i).toString()
      else text1 = "-"
      if (dieces._2.length > i) text2 = dieces._2(i).toString()
      else text2 = "-"
      println(text.format(text1, text2))
    }
  }

  def printPlayerInitialisation = {
    println("-----------------------------------------------------------------------");
    val avColors = gameLogic.getAvailableColors.toString()
    println("Following colors are still available: " + avColors.substring(5, avColors.length() - 1));
    println("Pleas enter v to start the game or a name and color which is available (name, color) to create a player:");
  }

  def printSpreadTroops = {
    println("-----------------------------------------------------------------------");
    println("Troops to spread: " + gameLogic.getTroopsToSpread)
    printPitch
  }

  private def printPitch = {
    println("\n[" + gameLogic.getCurrentPlayer._1 + "]\n");

    val countries = gameLogic.getCountries

    for (c: (String, String, Int) <- countries) {

      val s1 = c._1;

      val s2 = c._2;

      val s3 = c._3;

      val p1 = LENGTH - s1.length();

      val p2 = LENGTH;
      val text: String = "%s:%" + p1 + "s%" + p2 + "d\n"

      println(text.format(s1, s2, s3));

    }

    println("\n_______________________________________________________________________\n");

    println("q:     quit              country1, country2:     attack\nn:     new game          country, x:             recruit\ne:     end turn          country:                show candidates");

    println("_______________________________________________________________________\n");
  }
}