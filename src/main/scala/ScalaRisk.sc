import de.htwg.se.scala_risk.controller.impl.GameLogic
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.impl.Colors
import de.htwg.se.scala_risk.controller.impl.StartState
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.view.TUI

object ScalaRiskWorksheet {
	ScalaRisk.getInstance()                   //> res0: SRisk = ScalaRisk$Risk@726f3b58
	val gl = new GameLogic                    //> gl  : de.htwg.se.scala_risk.controller.impl.GameLogic = de.htwg.se.scala_ris
                                                  //| k.controller.impl.GameLogic@21bcffb5
	Colors.values.exists { _.toString() == "BL" }
                                                  //> res1: Boolean = false
	gl.setPlayers(List(("Test", "BLUE")))
	gl.setPlayers(List(("Test1", "RED")))
	gl.setPlayers(List(("Test2", "YELLOW")))
	gl.setPlayers(List(("Test3", "GREEN")))
	 Players.playerList                       //> res2: scala.collection.mutable.ArrayBuffer[de.htwg.se.scala_risk.model.Playe
                                                  //| r] = ArrayBuffer(Player(Test,BLUE), Player(Test1,RED), Player(Test2,YELLOW),
                                                  //|  Player(Test3,GREEN))
	val tui = new TUI(gl)                     //> tui  : de.htwg.se.scala_risk.view.TUI = de.htwg.se.scala_risk.view.TUI@dc245
                                                  //| 21
	gl.initializeGame()                       //> 
                                                  //| [Test]
                                                  //| 
                                                  //| -----------------------------------------------------------------------
                                                  //| 
                                                  //| VENEZUELA:                Test1                             3
                                                  //| 
                                                  //| PERU:                     Test3                             3
                                                  //| 
                                                  //| ARGENTINIEN:              Test3                             3
                                                  //| 
                                                  //| BRASILIEN:                Test2                             3
                                                  //| 
                                                  //| NORDAFRIKA:                Test                             3
                                                  //| 
                                                  //| ZENTRALAFRIKA:            Test1                             3
                                                  //| 
                                                  //| SUEDAFRIKA:                Test                             3
                                                  //| 
                                                  //| MADAGASKAR:               Test1                             3
                                                  //| 
                                                  //| OSTAFRIKA:                 Test                             3
                                                  //| 
                                                  //| AEGYPTEN:                 Test2                             3
                                                  //| 
                                                  //| 
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| q:     quit              country1, country2:     attack
                                                  //| n:     new game          country, x:             recruit
                                                  //| e:     end turn          country:                show candidates
                                                  //| _______________________________________________________________________
                                                  //| 
	gl.getCountries()
	Players.nextPlayer()
	Players.nextPlayer()
	Players.nextPlayer()
	Players.nextPlayer()
}