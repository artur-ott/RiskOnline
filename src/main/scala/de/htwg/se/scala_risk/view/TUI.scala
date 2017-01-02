package de.htwg.se.scala_risk.view

import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.util.observer.TObserver

class TUI (gameLogic: GameLogic) extends TObserver {
  val LENGTH = 30
  
  gameLogic.add(this)
  def setNextInput(input:String):Boolean = {
    if (!input.equals("q"))
      return true
    return false
  }
  
  def update() = printTui
  
  private def printTui = {
    println("\n["+gameLogic.getCurrentPlayer._1+"]\n");

		/*println("-----------------------------------------------------------------------");

		println(">>>"+gameLogic.getStatus());*/

		println("-----------------------------------------------------------------------\n");

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