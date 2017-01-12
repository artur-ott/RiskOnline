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
import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.model.World.Countries

import java.awt.event.MouseAdapter


object GUIJava {
    def main(args: Array[String]) {
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
  /* Boolean that determines if the game is stopped */
  var running = true
  /* Define the buttons and labels */
  val gameStatusLabel = new JLabel("GameStatus Label")
  val endTurnButton = new JButton("Zug beenden") {/*TODO: what the button should do when clicked*/}
  val map_grey = getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/map_grey.jpg")),
                                1238, 810)
  /* Map to be displayed (BufferedImage) */
  val map_legend = getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/map_legend.png")),
                  1238, 810)
               
  /* Reference map (BufferedImage) with different color for each country to determine
   * the country the player selected.
   */
  val map_ref = getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/map_ref.png")),
                               1238, 810)
  /* Map as a Label (Component) */
  val map = getMap()
  
  /* Build the frame */
  this.setTitle("Risiko")
  this.setPreferredSize(new Dimension(1238,950))
  this.setResizable(false)
  this.setJMenuBar(new GUIMenuBar(this))
  val x0 = new JPanel()
  
  /* County labels */
  /* Nordamerika */
  val alaska       = new JLabel("0", SwingConstants.CENTER) {this.setBounds(73-15, 126-15, 30, 30)}
  val nwt          = new JLabel("0", SwingConstants.CENTER) {this.setBounds(187-15, 128-15, 30, 30)}
  val alberta      = new JLabel("0", SwingConstants.CENTER) {this.setBounds(171-15, 187-15, 30, 30)}
  val ontario      = new JLabel("0", SwingConstants.CENTER) {this.setBounds(247-15, 210-15, 30, 30)}
  val groenland    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(408-15, 88-15, 30, 30)}
  val ostkanada    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(323-15, 215-15, 30, 30)}
  val weststaaten  = new JLabel("0", SwingConstants.CENTER) {this.setBounds(174-15, 281-15, 30, 30)}
  val oststaaten   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(253-15, 319-15, 30, 30)}
  val mittelamerika= new JLabel("0", SwingConstants.CENTER) {this.setBounds(210-15, 410-15, 30, 30)}
  /* Suedamerika */
  val venezuela    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(268-15, 468-15, 30, 30)}
  val peru         = new JLabel("0", SwingConstants.CENTER) {this.setBounds(293-15, 572-15, 30, 30)}
  val brasilien    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(371-15, 555-15, 30, 30)}
  val argentinien  = new JLabel("0", SwingConstants.CENTER) {this.setBounds(291-15, 689-15, 30, 30)}
  /* Afrika */
  val nordafrika   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(558-15, 523-15, 30, 30)}
  val aegypten     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(651-15, 484-15, 30, 30)}
  val ostafrika    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(726-15, 584-15, 30, 30)}
  val zentralafrika= new JLabel("0", SwingConstants.CENTER) {this.setBounds(652-15, 624-15, 30, 30)}
  val suedafrika   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(651-15, 732-15, 30, 30)}
  val madagaskar   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(763-15, 736-15, 30, 30)}
  /* Europa */
  val westeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(509-15, 382-15, 30, 30)}
  val suedeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(624-15, 355-15, 30, 30)}
  val nordeuropa   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(600-15, 275-15, 30, 30)}
  val grossbrit    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(498-15, 265-15, 30, 30)}
  val island       = new JLabel("0", SwingConstants.CENTER) {this.setBounds(510-15, 164-15, 30, 30)}
  val skandinavien = new JLabel("0", SwingConstants.CENTER) {this.setBounds(594-15, 170-15, 30, 30)}
  val russland     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(704-15, 232-15, 30, 30)}
  /* Asien */
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
  /* Australien */
  val indonesien   = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1008-15, 596-15, 30, 30)}
  val neuguinea    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1114-15, 576-15, 30, 30)}
  val westaustr    = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1053-15, 731-15, 30, 30)}
  val ostaustr     = new JLabel("0", SwingConstants.CENTER) {this.setBounds(1165-15, 735-15, 30, 30)}

  val countryArray = Array(alaska, nwt, alberta, ontario, ostkanada, groenland, weststaaten, oststaaten, mittelamerika,
                           venezuela, peru, brasilien, argentinien, nordafrika, aegypten, ostafrika, zentralafrika,
                           suedafrika, madagaskar, westeuropa, suedeuropa, nordeuropa, grossbrit, island,
                           skandinavien, russland, mittosten, afghanistan, ural, sibirien, jakutien,
                           kamtschaka, japan, mongolei, irkutsk, china, indien, suedostasien, indonesien,
                           neuguinea, westaustr, ostaustr)
  x0.setLayout(new BorderLayout())

  val x1 = new JPanel()
  x1.setLayout(new BoxLayout(x1, BoxLayout.Y_AXIS))
  map.setLayout(null)
  countryArray.foreach { x => map.add(x) }
  x1.add(map)
  
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
       this.setIcon(new ImageIcon(map_grey))
        //this.icon = new ImageIcon(map_)
       this.addMouseListener(new MouseAdapter() {
         override def mouseClicked(e:MouseEvent) {
           if (running) {
             /* Determine the color of the country in the reference map */
             val i = map_ref.getRGB(e.getPoint.x, e.getPoint.y)
             println(map_ref.getRGB(e.getPoint.x, e.getPoint.y))
             val c = new Color(i)
             val r = c.getRed
             val g = c.getGreen
             val b = c.getBlue
             println(r,g,b) 
              
             val country = getSelectedCountry(i)
             updateTroopCount(country)
             repaintCountry(country, "RED")             
           }

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
  
  def getSelectedCountry(c:Int) : JLabel = {
    var selectedCountry = null.asInstanceOf[JLabel]
    /* Nordamerika */
    if (c == -3993841) {selectedCountry = alaska}
    else if (c == -772811) {selectedCountry = nwt}
    else if (c == -41635) {selectedCountry = groenland}
    else if (c == -1245184) {selectedCountry = alberta}
    else if (c == -3459787) {selectedCountry = ontario}
    else if (c == -4390912) {selectedCountry = ostkanada}
    else if (c == -5373952) {selectedCountry = weststaaten}
    else if (c == -57312) {selectedCountry = oststaaten}
    else if (c == -196608) {selectedCountry = mittelamerika}
    /* Suedamerika */
    else if (c == -923904) {selectedCountry = venezuela}
    else if (c == -164) {selectedCountry = peru}
    else if (c == -256) {selectedCountry = brasilien}
    else if (c == -5888) {selectedCountry = argentinien}
    /* Afrika */
    else if (c == -2987746) {selectedCountry = nordafrika}
    else if (c == -8834304) {selectedCountry = aegypten}
    else if (c == -7650029) {selectedCountry = zentralafrika}
    else if (c == -32988) {selectedCountry = ostafrika}
    else if (c == -3308234) {selectedCountry = suedafrika}
    else if (c == -3316195) {selectedCountry = madagaskar}
    /* Europa */
    else if (c == -16771449) {selectedCountry = westeuropa}
    else if (c == -16775517) {selectedCountry = grossbrit}
    else if (c == -16772762) {selectedCountry = island}
    else if (c == -16761601) {selectedCountry = suedeuropa}
    else if (c == -16775425) {selectedCountry = nordeuropa}
    else if (c == -8350209) {selectedCountry = skandinavien}
    else if (c == -12690973) {selectedCountry = russland}
    /* Asien */
    else if (c == -15559131) {selectedCountry = mittosten}
    else if (c == -14878917) {selectedCountry = afghanistan}
    else if (c == -16729574) {selectedCountry = ural}
    else if (c == -10420362) {selectedCountry = sibirien}
    else if (c == -14962127) {selectedCountry = jakutien}
    else if (c == -15103447) {selectedCountry = kamtschaka}
    else if (c == -13831608) {selectedCountry = japan}
    else if (c == -16711900) {selectedCountry = irkutsk}
    else if (c == -11941285) {selectedCountry = mongolei}
    else if (c == -11615650) {selectedCountry = china}
    else if (c == -13708987) {selectedCountry = indien}
    else if (c == -13318071) {selectedCountry = suedostasien}
    /* Australien */
    else if (c == -29696) {selectedCountry = indonesien}
    else if (c == -37888) {selectedCountry = neuguinea}
    else if (c == -23296) {selectedCountry = westaustr}
    else if (c == -17612) {selectedCountry = ostaustr}
    
    return selectedCountry
  }
  
  
  
  var counter = 0
  def repaintCountry(country:JLabel, color:String) {

    if (country != null) {
      var intcolor = 0
      counter match {
        case 0 => intcolor = -57312
        case 1 => intcolor = -5888
        case 2 => intcolor =  -10420362
        case 3 => intcolor = -8350209
        case _ => intcolor = 0
      }
      counter += 1
      counter %= 4
//      color match {
//        case "RED" => intcolor = -57312
//        case "YELLOW" => intcolor = -5888
//        case "GREEN" => intcolor =  -10420362
//        case "BLUE" => intcolor = -8350209
//        case _ => intcolor = 0
//      }
      println("Color: " + color)
      if (intcolor != 0) {
        println("not null")
        val xloc = country.getLocation().getX.toInt+15
        val yloc = country.getLocation().getY.toInt+15
        val c = map_ref.getRGB(xloc, yloc)
        println(c)
        for (y <- 0 to map_grey.getHeight-1) {
          for (x <- 0 to map_grey.getWidth-1) {       
            if (map_ref.getRGB(x, y) == c) {
              map_grey.setRGB(x, y, intcolor)
            }
          }
        }          
      }
    }
    
    map.repaint()
      //this.map.setIcon(new ImageIcon(map_legend))
   
    
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



