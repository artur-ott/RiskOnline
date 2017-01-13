package de.htwg.se.scala_risk.view

import javax.swing._
import java.awt._
import javax.imageio.ImageIO
import java.io.File
import java.awt._
import java.awt.event.ActionListener
import java.awt.event.ItemListener
import java.awt.event.ItemEvent
import java.awt.event.ActionEvent
import java.awt.event.ItemEvent
import de.htwg.se.scala_risk.controller.GameLogic
import de.htwg.se.scala_risk.model.World.Players


class EnterPlayers(gameLogic : GameLogic) extends JFrame with ActionListener with ItemListener {
  this.setTitle("Spieler eingeben")
  this.setResizable(false)
  val backgroundImage = Scale.getScaledImage(ImageIO.read(new File("src/main/scala/de/htwg/se/scala_risk/view/enter_player.PNG")),
                                             1169, 780)
  
  var actionListenerActive = true
  val x0 = new JPanel()
  x0.setLayout(new BorderLayout())
 
  

  val enter_player_background = new JLabel() {setIcon(new ImageIcon(backgroundImage))}
  enter_player_background.setLayout(null)
  
  val continueButton = new JButton("START") {this.setBounds(1000, 307, 100, 425)}
  continueButton.addActionListener(this)
  
  val player3Check = new JCheckBox("") {this.setBounds(430, 447+24, 20, 20)}
  val player4Check = new JCheckBox("") {this.setBounds(430, 518+24, 20, 20)}
  val player5Check = new JCheckBox("") {this.setBounds(430, 589+24, 20, 20)}
  val player6Check = new JCheckBox("") {this.setBounds(430, 660+24, 20, 20)}
  
  val checkList = Array(player3Check, player4Check, player5Check, player6Check)
  checkList.foreach { x => x.setOpaque(false); x.addItemListener(this) } 
  
  
  val player1Name = new JTextField("")
  val player2Name = new JTextField("")
  val player3Name = new JTextField("") {this.setVisible(false)}
  val player4Name = new JTextField("") {this.setVisible(false)}
  val player5Name = new JTextField("") {this.setVisible(false)}
  val player6Name = new JTextField("") {this.setVisible(false)}
  
  val colors = gameLogic.getAvailableColors.toArray
  var test = Array("", "RED", "YELLOW", "GREEN", "BLUE", "PINK", "ORANGE")
  
  val player1Color = new JComboBox(test)
  val player2Color = new JComboBox(test)
  val player3Color = new JComboBox(test) {this.setVisible(false)}
  val player4Color = new JComboBox(test) {this.setVisible(false)}
  val player5Color = new JComboBox(test) {this.setVisible(false)}
  val player6Color = new JComboBox(test) {this.setVisible(false)}
  
  val comboArray = Array(player1Color, player2Color, player3Color, 
                         player4Color, player5Color, player6Color) 
  comboArray.foreach { x => x.addActionListener(this)}
  
  val playerGrid = new JPanel() {
    this.setLayout(new GridLayout(6,3))
    this.add(player1Name); this.add(new JLabel("")); this.add(player1Color)
    this.add(player2Name); this.add(new JLabel("")); this.add(player2Color)
    this.add(player3Name); this.add(new JLabel("")); this.add(player3Color)
    this.add(player4Name); this.add(new JLabel("")); this.add(player4Color)
    this.add(player5Name); this.add(new JLabel("")); this.add(player5Color)
    this.add(player6Name); this.add(new JLabel("")); this.add(player6Color)
    this.setOpaque(false)
    this.setBounds(497, 304, 440, 428)
  }
  enter_player_background.add(playerGrid)
  enter_player_background.add(continueButton)
  enter_player_background.add(player3Check)
  enter_player_background.add(player4Check)
  enter_player_background.add(player5Check)
  enter_player_background.add(player6Check)
  
  x0.add(enter_player_background, BorderLayout.NORTH)
  this.setContentPane(x0)
  this.pack()
  
  var liste = scala.collection.immutable.List[String]()
  override def actionPerformed(e:ActionEvent) {
    if (this.actionListenerActive) {
      if (comboArray.contains(e.getSource)) {
        this.actionListenerActive = false

        val selectedItem = e.getSource().asInstanceOf[JComboBox[Array[String]]].getSelectedItem().toString()
        println("Selected item:" , selectedItem)
        var li = scala.collection.immutable.List[String]()
        
        comboArray.foreach { x => if (x.getSelectedItem != "" && !li.contains(x.getSelectedItem)) {li = li.::(x.getSelectedItem.toString())} }
        li.foreach { x => println(x) }
        comboArray.foreach { x => {
                                    val src = x.getSelectedItem.toString()
                                    x.removeAllItems()
                                    test.foreach { y => if(!li.contains(y)) {x.addItem(y)}}
                                    if(src != "") {x.addItem(src); x.setSelectedItem(src)}
                                    }
                                  }
      this.actionListenerActive = true  
      }
      
      if (e.getSource == continueButton) {
        if (player1Name.getText.isEmpty() || player1Color.getSelectedItem.toString().isEmpty() ||
            player2Name.getText.isEmpty() || player2Color.getSelectedItem.toString().isEmpty()) {
          JOptionPane.showMessageDialog(this, "Bitte für mindestens 2 Spieler Name und Farbe eingeben!",
                                        "Spieler unvollständig", JOptionPane.ERROR_MESSAGE)
        } else {
          this.setVisible(false)
          Players.addPlayer(player1Name.getText, player1Color.getSelectedItem.toString())
          Players.addPlayer(player2Name.getText, player2Color.getSelectedItem.toString())
          if (player3Check.isSelected()) {Players.addPlayer(player3Name.getText, player3Color.getSelectedItem.toString())}
          if (player4Check.isSelected()) {Players.addPlayer(player4Name.getText, player4Color.getSelectedItem.toString())}
          if (player5Check.isSelected()) {Players.addPlayer(player5Name.getText, player5Color.getSelectedItem.toString())}
          if (player6Check.isSelected()) {Players.addPlayer(player6Name.getText, player6Color.getSelectedItem.toString())}
          new GUI(gameLogic)         
          
          
          
        }

      

      }
    }
  }
  
  override def itemStateChanged(e : ItemEvent) {
    if (e.getSource() == player3Check) {
      if (e.getStateChange == ItemEvent.SELECTED) {player3Name.setVisible(true); player3Color.setVisible(true)}
      else {player3Name.setVisible(false); player3Color.setVisible(false); player3Color.setSelectedItem("")}
    } 
    if (e.getSource() == player4Check) {
      if (e.getStateChange == ItemEvent.SELECTED) {player4Name.setVisible(true); player4Color.setVisible(true)}
      else {player4Name.setVisible(false); player4Color.setVisible(false); player4Color.setSelectedItem("")}
    } 
    if (e.getSource() == player5Check) {
      if (e.getStateChange == ItemEvent.SELECTED) {player5Name.setVisible(true); player5Color.setVisible(true)}
      else {player5Name.setVisible(false); player5Color.setVisible(false); player5Color.setSelectedItem("")}
    } 
    if (e.getSource() == player6Check) {
      if (e.getStateChange == ItemEvent.SELECTED) {player6Name.setVisible(true); player6Color.setVisible(true)}
      else {player6Name.setVisible(false); player6Color.setVisible(false); player6Color.setSelectedItem("")}
    }
     
  }
}