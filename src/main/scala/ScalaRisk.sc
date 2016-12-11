import de.htwg.se.scala_risk.controller.impl.GameLogic
import de.htwg.se.scala_risk.model.Players
import de.htwg.se.scala_risk.model.impl.Colors

object ScalaRiskWorksheet {
	ScalaRisk.getInstance()                   //> res0: SRisk = ScalaRisk$Risk@7e774085
	val gl = new GameLogic                    //> gl  : de.htwg.se.scala_risk.controller.impl.GameLogic = de.htwg.se.scala_ris
                                                  //| k.controller.impl.GameLogic@13969fbe
	Colors.values.exists { _.toString() == "BL" }
                                                  //> res1: Boolean = false
	gl.setPlayers(List(("Test", "BL")))       //> Color already taken!
                                                  //| RED
                                                  //| YELLOW
                                                  //| GREEN
                                                  //| BLUE
	Players.playerList                        //> res2: List[de.htwg.se.scala_risk.model.impl.Player] = List()
}