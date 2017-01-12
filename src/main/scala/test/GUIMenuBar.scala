package test

import javax.swing._
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class GUIMenuBar extends JMenuBar with ActionListener {
	/* Create menu. */
	val menu = new JMenu("Spiel");
	menu.setMnemonic(KeyEvent.VK_D);
		
	/* Create item1. */
	val item1 = new JMenuItem("Read");
	item1.addActionListener(this);
		
	/* Create item2. */
	val item2 = new JMenuItem("Beenden");
	item2.addActionListener(this);
	
	
	/* Add items to menu. */
	menu.add(item1);
	menu.addSeparator();
	menu.add(item2);
	
	this.add(menu)
	
	override def actionPerformed(e:ActionEvent) {
	  if (e.getSource == item2) {
	    val c = JOptionPane.showConfirmDialog(
	              this.getParent, 
	              "Wollen Sie das Spiel wirklich beenden?",
	              "Beenden?",
	              JOptionPane.YES_NO_OPTION)
	    if (c == JOptionPane.YES_OPTION) {
	      System.exit(0)
	    }
	    
	  }
	}
}