import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.view.TUI
import de.htwg.se.scala_risk.view.WelcomeScreen
import com.google.inject.Guice
import de.htwg.se.scala_risk.model.impl.{ World => ImplWorld }


import java.io.File;
import java.util.concurrent.TimeUnit;
 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


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
    
    /* Add background Music */
    val clip = AudioSystem.getClip();
	  clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource(("/music/heart_of_courage.wav"))))
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    Thread.sleep(10000);
   
    while (tui.setNextInput(scala.io.StdIn.readLine())) {}
    
  }
}