package de.htwg.se.scala_risk.view

import javax.swing._
import java.awt._
import javax.imageio.ImageIO
import java.io.File
import java.awt._
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import de.htwg.se.scala_risk.controller.GameLogic

class Dices(gameLogic : GameLogic) extends JFrame with ActionListener {
  
  
  this.setTitle("Würfeln...")
  this.setResizable(true)
  val backgroundImage = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/dices_background.jpg")),
                                             504, 753)
  val dice1_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/1.jpg")),
                                       95, 95)
  val dice2_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/2.jpg")),
                                       95, 95)
  val dice3_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/3.jpg")),
                                       95, 95)          
  val dice4_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/4.jpg")),
                                       95, 95)
  val dice5_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/5.jpg")),
                                       95, 95) 
  val dice6_img = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/6.jpg")),
                                       95, 95)                                       
  var actionListenerActive = true
  val x0 = new JPanel()
  x0.setLayout(new BorderLayout())
 
  

  val dices_background = new JLabel() {setIcon(new ImageIcon(backgroundImage))}    
  
  val panelName = new JPanel()
  panelName.setLayout(new GridLayout(3,3))
  panelName.setBounds(43, 118, 390, 200) //43 417
  
  val country1Label = new JLabel("country1", SwingConstants.CENTER) 
  val country2Label = new JLabel("country2", SwingConstants.CENTER)
  val player1Name = new JLabel("player1", SwingConstants.CENTER)
  val player2Name = new JLabel("player2", SwingConstants.CENTER)
  
  
  dices_background.setLayout(null)
  

  panelName.add(player1Name)
  panelName.add(new JLabel(""))
  panelName.add(player2Name)
  panelName.add(country1Label)
  panelName.add(new JLabel(""))
  panelName.add(country2Label)
  panelName.add(new JLabel(""))
  panelName.add(new JLabel(""))
  panelName.add(new JLabel(""))
  panelName.setOpaque(false)
  
  val dice_1 = new JLabel() {this.setIcon(new ImageIcon(dice1_img)); this.setBounds(75, 338, 95, 95)}
  val dice_2 = new JLabel() {this.setIcon(new ImageIcon(dice2_img)); this.setBounds(75, 448, 95, 95)}
  val dice_3 = new JLabel() {this.setIcon(new ImageIcon(dice3_img)); this.setBounds(75, 558, 95, 95)}
  val dice_4 = new JLabel() {this.setIcon(new ImageIcon(dice4_img)); this.setBounds(335, 338, 95, 95)}
  val dice_5 = new JLabel() {this.setIcon(new ImageIcon(dice5_img)); this.setBounds(335, 448, 95, 95)}
  val dice_6 = new JLabel() {this.setIcon(new ImageIcon(dice6_img)); this.setBounds(335, 558, 95, 95)}
  val roll_dice = new JButton("Würfeln") {this.setBounds(175, 270, 155, 40 )}
  roll_dice.addActionListener(this)
  val diceArray = Array(dice_1, dice_2, dice_3, dice_4, dice_5, dice_6)
  diceArray.foreach { x => dices_background.add(x)}
  dices_background.add(roll_dice)
 
//  panelName.add(new JLabel(""))
//  panelName.add(player2Name)
//  panelName.add(new JLabel("dsafasdf"))
  dices_background.add(panelName)
  
  
  
  
  x0.add(dices_background, BorderLayout.NORTH)
  this.setContentPane(x0)
  this.pack()

  override def actionPerformed(e : ActionEvent) {
    if (e.getSource == roll_dice) {
      //TODO: IMPLEMENTIEREN
    }
  }
  
}