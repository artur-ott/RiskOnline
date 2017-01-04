package de.htwg.se.scala_risk.view

import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.util.observer.TObserver
import de.htwg.se.scala_risk.util.Statuses

class TUI (gameLogic: GameLogic) extends TObserver {
  val LENGTH = 30
  
  gameLogic.add(this)
  if (gameLogic.getStatus == Statuses.CREATE_GAME) {
    println("\n_______________________________________________________________________\n");
    println("\n______________________To start the game press s________________________\n");
    println("\n_______________________________________________________________________\n");
  }
  
  def setNextInput(input:String):Boolean = {
    gameLogic.getStatus match {
      case Statuses.CREATE_GAME => if (input.equals("s")) gameLogic.startGame
      case Statuses.INITIALIZE_PLAYERS => this.parsePlayer(input)
      case Statuses.GAME_INITIALIZED =>
    }
    if (!input.equals("q"))
      return true
    return false
  }
  
  def parsePlayer(player:String) {
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
        
      // Errors
      case Statuses.COUNTRY_DOES_NOT_BELONG_TO_PLAYER => println("COUNTRY_DOES_NOT_BELONG_TO_PLAYER")
      case Statuses.NOT_ENOUGH_TROOPS_TO_SPREAD => println("NOT_ENOUGH_TROOPS_TO_SPREAD")
      case Statuses.COUNTRY_NOT_FOUND => println("COUNTRY_NOT_FOUND")
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
    println("\n["+gameLogic.getCurrentPlayer._1+"]\n");

		val countries = gameLogic.getCountries
		
		for (c:(String, String, Int) <- countries) {

			val s1 = c._1;

			val s2 = c._2;

			val s3 =  c._3;

		  val p1 = LENGTH - s1.length();

			val p2 = LENGTH;
			val text:String = "%s:%"+p1+"s%"+p2+"d\n"

			println(text.format(s1, s2, s3));

		}

		println("\n_______________________________________________________________________\n");

		println("q:     quit              country1, country2:     attack\nn:     new game          country, x:             recruit\ne:     end turn          country:                show candidates");

		println("_______________________________________________________________________\n");
  }
}