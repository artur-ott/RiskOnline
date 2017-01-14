import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.controller.impl.{GameLogic => ImplGameLogic}
import de.htwg.se.scala_risk.view.TUI
import de.htwg.se.scala_risk.view.WelcomeScreen

trait SRisk

object ScalaRisk {
  private[this] var risk: Risk = _

  private final class Risk extends SRisk {

  }

  def getInstance(): SRisk =
    if (this.risk != null)
      this.risk.asInstanceOf[Risk]
    else {
      this.risk = new Risk
      return this.risk
    }

  def main(args: Array[String]): Unit = {
    val gl: GameLogic = new ImplGameLogic
    val tui: TUI = new TUI(gl)
    val gui: WelcomeScreen = new WelcomeScreen(gl)
    gui.setLocationRelativeTo(null)
    gui.setVisible(true)
    while (tui.setNextInput(scala.io.StdIn.readLine())) {}
  }
}