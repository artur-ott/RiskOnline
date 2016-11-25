package de.htwg.se.scala_risk.model.impl

case class Country(name : String, var neighboring_countries : Set[Country], 
                   var troops : Int, var owner : Player) {
  override def toString(): String = {
    var neighbors:String="" 
    neighboring_countries.foreach{ c=> neighbors+=c.name + " " }
     "Name: " + name + ", Neigbors: " + neighbors+ ", troops: " + troops+ ", owner: " + owner.name
  }
}


object Countries {
//  val country1 = Country("ALASKA", Set.empty, 0, Player("Test", Colors.RED))
//  val country2 = Country("NORDWEST-TERRITORIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country3 = Country("ALBERTA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country4 = Country("ONTARIO", Set.empty, 0,  Player("Test", Colors.RED))
//  val country5 = Country("GROENLAND", Set.empty, 0, Player("Test", Colors.RED))
//  val country6 = Country("OSTKANADA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country7 = Country("WESTSTAATEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country8 = Country("OSTSTAATEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country9 = Country("MITTELAMERIKA", Set.empty, 0, Player("Test", Colors.RED))
  val country10 = Country("VENEZUELA", Set.empty, 0, Players.Player1)
  val country11 = Country("PERU", Set.empty, 0, Players.Player1)
  val country12 = Country("ARGENTINIEN", Set.empty, 0, Players.Player1)
  val country13 = Country("BRASILIEN", Set.empty, 0, Players.Player1)
  val country14 = Country("NORDAFRIKA", Set.empty, 0, Players.Player2)
  val country15 = Country("ZENTRALAFRIKA", Set.empty, 0, Players.Player2)
  val country16 = Country("SUEDAFRIKA", Set.empty, 0, Players.Player2)
  val country17 = Country("MADAGASKAR", Set.empty, 0, Players.Player1)
  val country18 = Country("OSTAFRIKA", Set.empty, 0,Players.Player2)
  val country19 = Country("AEGYPTEN", Set.empty, 0, Players.Player2)
//  val country20 = Country("SUEDEUROPA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country21 = Country("WESTEUROPA", Set.empty, 0, Player("Test", Colors.RED))
//  val country22 = Country("NORDEUROPA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country23 = Country("GROSSBRITANNIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country24 = Country("ISLAND", Set.empty, 0,  Player("Test", Colors.RED))
//  val country25 = Country("SKANDINAVIEN", Set.empty, 0, Player("Test", Colors.RED))
//  val country26 = Country("RUSSLAND", Set.empty, 0,  Player("Test", Colors.RED))
//  val country27 = Country("URAL", Set.empty, 0,  Player("Test", Colors.RED))
//  val country28 = Country("SIBIRIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country29 = Country("JAKUTIEN", Set.empty, 0, Player("Test", Colors.RED))
//  val country30 = Country("IRKUTSK", Set.empty, 0,  Player("Test", Colors.RED))
//  val country31 = Country("KAMTSCHAKA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country32 = Country("JAPAN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country33 = Country("MONGOLEI", Set.empty, 0, Player("Test", Colors.RED))
//  val country34 = Country("CHINA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country35 = Country("AFGHANISTAN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country36 = Country("MITTLERER-OSTEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country37 = Country("INDIEN", Set.empty, 0, Player("Test", Colors.RED))
//  val country38 = Country("SUEDOSTASIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country39 = Country("INDONESIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country40 = Country("NEUGUINEA", Set.empty, 0,  Player("Test", Colors.RED))
//  val country41 = Country("OSTAUSTRALIEN", Set.empty, 0,  Player("Test", Colors.RED))
//  val country42 = Country("WESTAUSTRALIEN", Set.empty, 0,  Player("Test", Colors.RED))
    
//  val n1 = Set(country2, country3, country4)
//  val n2 = Set(country1, country3, country4)
//  val n3 = Set(country1, country2)
//  val n4 = Set(country1, country2)
//  val n5 = Set(country2, country3, country4)
//  val n6 = Set(country1, country3, country4)
//  val n7 = Set(country1, country2)
//  val n8 = Set(country1, country2)
//  val n9 = Set(country2, country3, country4)
  val n10 = Set(country11, country13)
  val n11 = Set(country10, country12, country13)
  val n12 = Set(country11, country13)
  val n13 = Set(country10, country11, country12, country14)
  val n14 = Set(country13, country15, country18, country19)
  val n15 = Set(country14, country16, country18)
  val n16 = Set(country15, country17, country18)
  val n17 = Set(country16, country18)
  val n18 = Set(country14, country15, country16, country17, country19)
  val n19 = Set(country14, country18)
//  val n20 = Set(country1, country2)
//  val n21 = Set(country2, country3, country4)
//  val n22 = Set(country1, country3, country4)
//  val n23 = Set(country1, country2)
//  val n24 = Set(country1, country2)
//  val n25 = Set(country2, country3, country4)
//  val n26 = Set(country1, country3, country4)
//  val n27 = Set(country1, country2)
//  val n28 = Set(country1, country2)
//  val n29 = Set(country2, country3, country4)
//  val n30 = Set(country1, country3, country4)
//  val n31 = Set(country1, country2)
//  val n32 = Set(country1, country2)
//  val n33 = Set(country2, country3, country4)
//  val n34 = Set(country1, country3, country4)
//  val n35 = Set(country1, country2)
//  val n36 = Set(country1, country2)
//  val n37 = Set(country2, country3, country4)
//  val n38 = Set(country1, country3, country4)
//  val n39 = Set(country1, country2)
//  val n40 = Set(country1, country2)
//  val n39 = Set(country1, country2)
//  val n40 = Set(country1, country2)
//  val n41 = Set(country1, country2)
//  val n42 = Set(country1, country2)
  
//  country1.neighboring_countries = n1
//  country2.neighboring_countries = n2
//  country3.neighboring_countries = n3
//  country4.neighboring_countries = n4
//  country5.neighboring_countries = n1
//  country6.neighboring_countries = n2
//  country7.neighboring_countries = n3
//  country8.neighboring_countries = n4
//  country9.neighboring_countries = n1
  country10.neighboring_countries = n10
  country11.neighboring_countries = n11
  country12.neighboring_countries = n12
  country13.neighboring_countries = n13
  country14.neighboring_countries = n14
  country15.neighboring_countries = n15
  country16.neighboring_countries = n16
  country17.neighboring_countries = n17
  country18.neighboring_countries = n18
  country19.neighboring_countries = n19
//  country20.neighboring_countries = n4
//  country21.neighboring_countries = n1
//  country22.neighboring_countries = n2
//  country23.neighboring_countries = n3
//  country24.neighboring_countries = n4
//  country25.neighboring_countries = n1
//  country26.neighboring_countries = n2
//  country27.neighboring_countries = n3
//  country28.neighboring_countries = n4
//  country29.neighboring_countries = n1
//  country30.neighboring_countries = n2
//  country31.neighboring_countries = n3
//  country32.neighboring_countries = n4
//  country33.neighboring_countries = n1
//  country34.neighboring_countries = n2
//  country35.neighboring_countries = n3
//  country36.neighboring_countries = n4
//  country37.neighboring_countries = n1
//  country38.neighboring_countries = n2
//  country39.neighboring_countries = n3
//  country40.neighboring_countries = n4
//  country41.neighboring_countries = n3
//  country42.neighboring_countries = n4
}