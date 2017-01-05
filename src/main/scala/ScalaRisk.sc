import de.htwg.se.scala_risk.controller.impl.GameLogic
import de.htwg.se.scala_risk.controller.{GameLogic => TGameLogic}
import de.htwg.se.scala_risk.model.World.Players
import de.htwg.se.scala_risk.model.World.Countries
import de.htwg.se.scala_risk.view.TUI
import de.htwg.se.scala_risk.util.Statuses

object ScalaRiskWorksheet {
	ScalaRisk.getInstance()                   //> res0: SRisk = ScalaRisk$Risk@726f3b58
	val gl = new GameLogic                    //> gl  : de.htwg.se.scala_risk.controller.impl.GameLogic = de.htwg.se.scala_ris
                                                  //| k.controller.impl.GameLogic@153f5a29
	/*gl.setPlayer(("Test2", "YELLOW"))*/
	gl.getAvailableColors                     //> res1: List[String] = List(RED, YELLOW, GREEN, BLUE)
	 Players.playerList                       //> res2: scala.collection.mutable.ArrayBuffer[de.htwg.se.scala_risk.model.Playe
                                                  //| r] = ArrayBuffer()
	val tui = new TUI(gl)                     //> 
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| 
                                                  //| ______________________To start the game press s________________________
                                                  //| 
                                                  //| 
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| tui  : de.htwg.se.scala_risk.view.TUI = de.htwg.se.scala_risk.view.TUI@548e7
                                                  //| 350
	gl.setStatus(Statuses.INITIALIZE_PLAYERS) //> -----------------------------------------------------------------------
                                                  //| Following colors are still available: RED, YELLOW, GREEN, BLUE
                                                  //| Pleas enter v to start the game or a name and color which is available (name
                                                  //| , color) to create a player:
	gl.setPlayer(("Test", "BLUE"))
	gl.setPlayer(("Test1", "RED"))
  gl.initializeGame                               //> 
                                                  //| [Test]
                                                  //| 
                                                  //| VENEZUELA:                 Test                             0
                                                  //| 
                                                  //| PERU:                     Test1                             0
                                                  //| 
                                                  //| ARGENTINIEN:               Test                             0
                                                  //| 
                                                  //| BRASILIEN:                Test1                             0
                                                  //| 
                                                  //| NORDAFRIKA:                Test                             0
                                                  //| 
                                                  //| ZENTRALAFRIKA:            Test1                             0
                                                  //| 
                                                  //| SUEDAFRIKA:                Test                             0
                                                  //| 
                                                  //| MADAGASKAR:               Test1                             0
                                                  //| 
                                                  //| OSTAFRIKA:                 Test                             0
                                                  //| 
                                                  //| AEGYPTEN:                 Test1                             0
                                                  //| 
                                                  //| 
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| q:     quit              country1, country2:     attack
                                                  //| n:     new game          country, x:             recruit
                                                  //| e:     end turn          country:                show candidates
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| -----------------------------------------------------------------------
                                                  //| Troops to spread: 3
                                                  //| 
                                                  //| [Test]
                                                  //| 
                                                  //| VENEZUELA:                 Test                             0
                                                  //| 
                                                  //| PERU:                     Test1                             0
                                                  //| 
                                                  //| ARGENTINIEN:               Test                             0
                                                  //| 
                                                  //| BRASILIEN:                Test1                             0
                                                  //| 
                                                  //| NORDAFRIKA:                Test                             0
                                                  //| 
                                                  //| ZENTRALAFRIKA:            Test1                             0
                                                  //| 
                                                  //| SUEDAFRIKA:                Test                             0
                                                  //| 
                                                  //| MADAGASKAR:               Test1                             0
                                                  //| 
                                                  //| OSTAFRIKA:                 Test                             0
                                                  //| 
                                                  //| AEGYPTEN:                 Test1                             0
                                                  //| 
                                                  //| 
                                                  //| _______________________________________________________________________
                                                  //| 
                                                  //| q:     quit              country1, country2:     attack
                                                  //| n:     new game          country, x:             recruit
                                                  //| e:     end turn          country:                show candidates
                                                  //| _______________________________________________________________________
                                                  //| 
  gl.getCandidates()                              //> res3: List[(String, String, Int)] = List((VENEZUELA,Test,0), (ARGENTINIEN,Te
                                                  //| st,0), (NORDAFRIKA,Test,0), (SUEDAFRIKA,Test,0), (OSTAFRIKA,Test,0))
  gl.addTroops("VENEZUELA", 3)
  gl.getCandidates("VENEZUELA")                   //> res4: List[(String, String, Int)] = List((PERU,"",0), (BRASILIEN,"",0))
  gl.attack("VENEZUELA", "BRASILIEN")             //> -----------------------------------------------------------------------
                                                  //| Test Test1
                                                  //| 4     6
                                                  //| 1     4
                                                  //| -     2
  gl.moveTroops(3)                                //> java.lang.ArrayIndexOutOfBoundsException: -1
                                                  //| 	at scala.collection.mutable.ResizableArray$class.apply(ResizableArray.sc
                                                  //| ala:44)
                                                  //| 	at scala.collection.mutable.ArrayBuffer.apply(ArrayBuffer.scala:48)
                                                  //| 	at de.htwg.se.scala_risk.controller.impl.GameLogic.moveTroops(GameLogic.
                                                  //| scala:184)
                                                  //| 	at ScalaRiskWorksheet$$anonfun$main$1.apply$mcV$sp(ScalaRiskWorksheet.sc
                                                  //| ala:23)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at ScalaRiskWorksheet$.main(ScalaRiskWorksheet.scala:8)
                                                  //| 	at ScalaRiskWorksheet.main(ScalaRiskWorksheet.scala)
  gl.setStatus(Statuses.GAME_INITIALIZED)
	 
}