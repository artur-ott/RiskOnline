package test

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
import java.awt._
import java.awt.event.MouseEvent

import java.awt.event.MouseAdapter


object GUIJava {
    def main(args: Array[String]) {
      println("bla")
    val gui = new GUI(null)
    gui.setVisible(true)

  }
}



class GUI (gameLogic : GameLogic) extends JFrame with TObserver {
  /* Register the GUI as a subscriber in the gameLogic.
   * As something changes in the gameLogic, the GUI
   * will be notified.
   */
  //gameLogic.add(this)
  

  /* Define the buttons and labels */
  val gameStatusLabel = new JLabel("GameStatus Label")
  val endTurnButton = new JButton("Zug beenden") {/*TODO: what the button should do when clicked*/}
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
  this.setTitle("Risiko")
  this.setPreferredSize(new Dimension(1238,950))
  this.setResizable(false)
  this.setJMenuBar(new GUIMenuBar)
  val x0 = new JPanel()
  
  /* County labels */
  val alaska       = new JLabel("0", SwingConstants.CENTER) {this.setBounds(73-15, 126-15, 30, 30)}
  val nwt          = new JLabel("0", SwingConstants.CENTER) {this.setBounds(187-15, 128-15, 30, 30)}
  val alberta      = new JLabel("0", SwingConstants.CENTER) {this.setBounds(171-15, 187-15, 30, 30)}
  val ontario      = new JLabel("0", SwingConstants.CENTER) {this.setBounds(247-15, 210-15, 30, 30)}
  val groenland    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(408-15, 88-15, 30, 30)}
  val ostkanada    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(323-15, 215-15, 30, 30)}
  val weststaaten  = new JLabel("0", SwingConstants.CENTER) {this.setBounds(174-15, 281-15, 30, 30)}
  val oststaaten   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(253-15, 319-15, 30, 30)}
  val mittelamerika= new JLabel("0", SwingConstants.CENTER) {this.setBounds(210-15, 410-15, 30, 30)}
  val venezuela    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(268-15, 468-15, 30, 30)}
  val peru         = new JLabel("0", SwingConstants.CENTER) {this.setBounds(293-15, 572-15, 30, 30)}
  val brasilien    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(371-15, 555-15, 30, 30)}
  val chile        = new JLabel("0", SwingConstants.CENTER) {this.setBounds(291-15, 689-15, 30, 30)}
  val nordafrika   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(558-15, 523-15, 30, 30)}
  val aegypten     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(651-15, 484-15, 30, 30)}
  val ostafrika    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(726-15, 584-15, 30, 30)}
  val zentralafrika= new JLabel("0", SwingConstants.CENTER) {this.setBounds(652-15, 624-15, 30, 30)}
  val suedafrika   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(651-15, 732-15, 30, 30)}
  val madagaskar   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(763-15, 736-15, 30, 30)}
  val westeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(509-15, 382-15, 30, 30)}
  val suedeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(624-15, 355-15, 30, 30)}
  val nordeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(600-15, 275-15, 30, 30)}
  val grossbrit    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(498-15, 265-15, 30, 30)}
  val island       = new JLabel("0", SwingConstants.CENTER) {this.setBounds(510-15, 164-15, 30, 30)}
  val skandinavien = new JLabel("0", SwingConstants.CENTER) {this.setBounds(594-15, 170-15, 30, 30)}
  val russland     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(704-15, 232-15, 30, 30)}
  val mittosten    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(741-15, 445-15, 30, 30)}
  val afghanistan  = new JLabel("0", SwingConstants.CENTER) {this.setBounds(815-15, 317-15, 30, 30)}
  val ural         = new JLabel("0", SwingConstants.CENTER) {this.setBounds(841-15, 206-15, 30, 30)}
  val sibirien     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(903-15, 159-15, 30, 30)}
  val jakutien     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(989-15, 109-15, 30, 30)}
  val kamtschaka   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1074-15, 114-15, 30, 30)}
  val japan        = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1114-15, 302-15, 30, 30)}
  val mongolei     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(994-15, 288-15, 30, 30)}
  val irkutsk      = new JLabel("0", SwingConstants.CENTER) {this.setBounds(970-15, 213-15, 30, 30)}
  val china        = new JLabel("0", SwingConstants.CENTER) {this.setBounds(965-15, 375-15, 30, 30)}
  val indien       = new JLabel("0", SwingConstants.CENTER) {this.setBounds(889-15, 446-15, 30, 30)}
  val suedostasien = new JLabel("0", SwingConstants.CENTER) {this.setBounds(990-15, 470-15, 30, 30)}
  val indonesien   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1008-15, 596-15, 30, 30)}
  val neuguinea    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1114-15, 576-15, 30, 30)}
  val westaustr    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1053-15, 731-15, 30, 30)}
  val ostaustr     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1165-15, 735-15, 30, 30)}

  val countryArray = Array(alaska, nwt, alberta, ontario, ostkanada, weststaaten, oststaaten, mittelamerika,
                           venezuela, peru, brasilien, chile, nordafrika, aegypten, ostafrika, zentralafrika,
                           suedafrika, madagaskar, westeuropa, suedeuropa, nordeuropa, grossbrit, island,
                           skandinavien, russland, mittosten, afghanistan, ural, sibirien, jakutien,
                           kamtschaka, japan, mongolei, irkutsk, china, indien, suedostasien, indonesien,
                           neuguinea, westaustr, ostaustr)
  x0.setLayout(new BorderLayout())

  val x1 = new JPanel()
  x1.setLayout(new BoxLayout(x1, BoxLayout.Y_AXIS))
  val m = getMap()
  m.setLayout(null)
  m.add(alaska)
  countryArray.foreach { x => m.add(x) }
  x1.add(m)
  
  val x2 = new JPanel()
  x2.setLayout(new GridLayout(1,4))
  x2.add(gameStatusLabel)
  x2.add(endTurnButton)
  
  x0.add(x1, BorderLayout.NORTH)
  x0.add(x2, BorderLayout.CENTER)
  x0.setPreferredSize(new Dimension(1238,950))
  this.setContentPane(x0)
  this.pack()
  this.setVisible(true)
  
  
  def getMap() : JLabel = {
     val map = new JLabel {
       this.setIcon(new ImageIcon(image_map))
        //this.icon = new ImageIcon(image_map)
       this.addMouseListener(new MouseAdapter() {
         override def mouseClicked(e:MouseEvent) {
          println("Mouse clicked!")
          println(e.getPoint)
          /* Determine the color of the country in the reference map */
          val i = map_ref.getRGB(e.getPoint.x, e.getPoint.y)
          val c = new Color(i)
          val r = c.getRed
          val g = c.getGreen
          val b = c.getBlue
          println(r,g,b) 
          println(i)
          
          val country = getSelectedCountry(r, g, b)
          updateTroopCount(country)
         }
       })
     }
     map.setPreferredSize(new Dimension(1238, 810))
     return map
  }   
  
  def updateTroopCount(country:JLabel) {
    if (country != null) {
      country.setText((Integer.valueOf(country.getText())+1).toString())
    }
    
  }
  
  def getSelectedCountry(r:Int, g:Int, b:Int) : JLabel = {
    var selectedCountry = null.asInstanceOf[JLabel]
    if (r == 174 && g == 0 && b == 0) {selectedCountry = weststaaten}
    if (r == 195 && g == 15 && b == 15) {selectedCountry = alaska
      println(map_ref.getHeight)
      println(map_ref.getWidth)
      println(new Color(map_ref.getRGB(0,1)))
      for (y <- 0 to map_ref.getHeight-1) {
        for (x <- 0 to map_ref.getWidth-1) {       
          if (new Color(map_ref.getRGB(x, y)) == new Color(195, 15, 15)) {
            image_map.setRGB(x, y, -16777216)
          }
        }
      }
      m.repaint()
//      this.m.setIcon(new ImageIcon(map_ref))
    }
    return selectedCountry
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



