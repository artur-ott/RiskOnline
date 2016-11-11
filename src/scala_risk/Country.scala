package scala_risk

case class Country(name : String, var neighboring_countries : Set[Country], 
                   var troops : Int, var owner : Player) {
  override def toString(): String = {
    var temp = "Name: " + name
    temp += ", Neigbors: "
    neighboring_countries.map { x => temp += x.name + " " }
    temp += ", troops: " + troops
    temp += ", owner: " + owner.name
    return temp;
  }
}


object Countries {
  val country1 = Country("Northern Europe", Set.empty, 0, Player("Test", Colors.RED))
  val country2 = Country("Southern Europe", Set.empty, 0,  Player("Test", Colors.RED))
  val country3 = Country("Western Europe", Set.empty, 0,  Player("Test", Colors.RED))
  val country4 = Country("Ukraine", Set.empty, 0,  Player("Test", Colors.RED))
    
  val n1 = Set(country2, country3, country4)
  val n2 = Set(country1, country3, country4)
  val n3 = Set(country1, country2)
  val n4 = Set(country1, country2)
  
  country1.neighboring_countries = n1
  country2.neighboring_countries = n2
  country3.neighboring_countries = n3
  country4.neighboring_countries = n4
  
}