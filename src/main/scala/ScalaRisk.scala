import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.view.TUI
import de.htwg.se.scala_risk.view.WelcomeScreen
import com.google.inject.Guice


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
    val injector = Guice.createInjector(new RiskInjector)
    import net.codingwell.scalaguice.InjectorExtensions._

    val tui: TUI = injector.instance[TUI]
    val gui: WelcomeScreen = injector.instance[WelcomeScreen]
    gui.setLocationRelativeTo(null)
    gui.setVisible(true)
    while (tui.setNextInput(scala.io.StdIn.readLine())) {}
  }
}