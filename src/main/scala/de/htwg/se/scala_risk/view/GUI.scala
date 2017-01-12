package de.htwg.se.scala_risk.view

import scala.swing._
import scala.swing.event._
import java.awt.Color
import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.util.observer.TObserver
import de.htwg.se.scala_risk.util.Statuses
import javax.swing.ImageIcon
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.io.File
import java.awt.Robot
import javax.swing._
import java.awt.GridLayout
import java.net.URL

import java.awt.event.MouseAdapter

object GUI extends MainFrame{
    def main(args: Array[String]) {
      println("bla")
    val gui = new GUI(null)
    gui.visible = true

  }
}



class GUI (gameLogic : GameLogic) extends MainFrame with TObserver {
  /* Register the GUI as a subscriber in the gameLogic.
   * As something changes in the gameLogic, the GUI
   * will be notified.
   */
  //gameLogic.add(this)
  

  /* Define the buttons and labels */
  val gameStatusLabel = new Label("GameStatus Label")
  val endTurnButton = Button("Zug beenden") {/*TODO: what the button should do when clicked*/}
  /* Map to be displayed (BufferedImage) */
  val image_map = getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/map_final.png")),
                  1238, 810)
  /* Reference map (BufferedImage) with different color for each country to determine
   * the country the player selected.
   */
  val map_ref = getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/map_ref_final.png")),
                               1238, 810)
  /* Map as a Label (Component) */
  val mapLabel = getMap()
  /* Build the frame */
  this.title = "Risiko"
  this.preferredSize = new Dimension(1238,950)
  this.resizable = false
  contents = new BorderPanel() {
    this.add(new BoxPanel(Orientation.Vertical) {
      //this.contents += mapLabel
      val ip = new ImagePanel(4,4)
      ip.preferredSize = new Dimension(1238, 810)
      ip.imagePath = "https://blog.flavia-it.de/wp-content/uploads/2013/10/scala-logo.png"
      ip.contents ++= Seq.tabulate(ip.rows * ip.columns)(i => new Label((i + 1).toString))
//      this.contents += new BorderPanel() {        
//        add(mapLabel, BorderPanel.Position.Center)
//      }
      contents+=ip
    }, BorderPanel.Position.North)
//    this.add(new GridPanel(1,4) {
//               contents += gameStatusLabel
//               contents += endTurnButton
//             }, BorderPanel.Position.Center)      
  }

  
  /* Prepare the map with mouselistener */
  def getMap() : Component = {
     val map = new Label {
       this.icon = new ImageIcon(image_map)
        //this.icon = new ImageIcon(image_map)
       
        listenTo(this.mouse.clicks)
        reactions += {
        case e: MouseClicked =>
          println("Mouse clicked!")
          /* Determine the color of the country in the reference map */
          val i = map_ref.getRGB(e.point.x, e.point.y)
          val c = new Color(i)
          val r = c.getRed
          val g = c.getGreen
          val b = c.getBlue
          println(r,g,b)        
        }
     }
     return map
  } 

  
  def  getScaledImage(srcImg:Image, w:Int, h:Int) : BufferedImage = {
    val resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    val g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
  }
  
 
  
  
  
  def update() = updateGUI
  
  def updateGUI() {
    
  }
}


class ImagePanel(rows0: Int, cols0: Int) extends GridPanel(rows0, cols0) {
  private var _imagePath = ""                                                 
  private var buf = Option.empty[BufferedImage]

  def imagePath = _imagePath
  def imagePath_=(value: String): Unit = {
    _imagePath = value
    buf.foreach(_.flush()); buf = None
    buf = Some(ImageIO.read(new URL(value)))
    repaint()
  }

  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    buf.foreach(g.drawImage(_, 0, 0, null))
  }
}
                                                                        








