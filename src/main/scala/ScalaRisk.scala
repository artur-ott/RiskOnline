import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.view.TUI
import de.htwg.se.scala_risk.view.WelcomeScreen
import com.google.inject.Guice
import de.htwg.se.scala_risk.model.impl.{ World => ImplWorld }

import de.htwg.se.scala_risk.controller.impl.{ GameLogic => ImplGameLogic }
object ScalaRisk {

  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new RiskInjector)
    import net.codingwell.scalaguice.InjectorExtensions._

    val world = new ImplWorld()
    //val world = new ImplWorld
    //val gameLogic = new ImplGameLogic(world)
    val tui: TUI = injector.instance[TUI]
    val gui: WelcomeScreen = injector.instance[WelcomeScreen]
    //val gui = new WelcomeScreen(gameLogic)
    gui.setLocationRelativeTo(null)
    gui.setVisible(true)
    while (tui.setNextInput(scala.io.StdIn.readLine())) {}
  }
}