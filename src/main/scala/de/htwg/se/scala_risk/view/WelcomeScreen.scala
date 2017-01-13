package de.htwg.se.scala_risk.view

import javax.swing._
import java.awt._
import javax.imageio.ImageIO
import java.io.File
import java.awt._
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import de.htwg.se.scala_risk.controller.GameLogic


class WelcomeScreen(gameLogic : GameLogic) extends JFrame with ActionListener {
   
  this.setTitle("SCALA_RISK")
  this.setResizable(false)
  val backgroundImage = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/welcome_screen.PNG")),
                        604, 905)

                        
  
  
  val x0 = new JPanel()
  x0.setLayout(new BorderLayout())
  

  val welcome_background = new JLabel() {setIcon(new ImageIcon(backgroundImage))}
  welcome_background.setLayout(null)


  val startButton = new JButton("SPIEL STARTEN") {this.setBounds(302-100, 500-50, 200, 100)}
  startButton.addActionListener(this)
  welcome_background.add(startButton)

  
  override def actionPerformed(e:ActionEvent) {
    this.setVisible(false)
    val ep = new EnterPlayers(gameLogic)
    ep.setVisible(true)
  }


  
  
//  val x1 = new JPanel()
//  x1.setLayout(new BoxLayout(x1, BoxLayout.Y_AXIS))
//  x1.add(welcome_background)
  
  x0.add(welcome_background, BorderLayout.NORTH)
  this.setContentPane(x0)
  this.pack()
  
  
  
 
  
                                 
}