package de.htwg.se.scala_risk.view

import javax.swing._
import java.awt._
import javax.imageio.ImageIO
import java.io.File
import java.awt._
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import de.htwg.se.scala_risk.controller.GameLogic

class Dices(gameLogic : GameLogic) extends JFrame {
  

   
  this.setTitle("Würfeln...")
  this.setResizable(false)
  val backgroundImage = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/dices_background.jpg")),
                                             504, 753)
  
  var actionListenerActive = true
  val x0 = new JPanel()
  x0.setLayout(new BorderLayout())
 
  

  val dices_background = new JLabel() {setIcon(new ImageIcon(backgroundImage))}    
  
  val country1Label = new JLabel("")
  val country2Label = new JLabel("")
  val player1Name = new JLabel("")
  val player2Name = new JLabel("")
  
  dices_background.setLayout(null)
  
  
  
  
  x0.add(dices_background, BorderLayout.NORTH)
  this.setContentPane(x0)
  this.pack()

  
}