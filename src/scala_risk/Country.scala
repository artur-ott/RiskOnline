package scala_risk

case class Country(name : String, var neighboring_countries : Set[Country], 
                   var troops : Int, var owner : Player) {}


object Countries {
  var country1 = Country("Northern Europe", null, 0, null)
  var country2 = Country("Southern Europe", null, 0, null)
  var country3 = Country("Western Europe", null, 0, null)
  var country4 = Country("Ukraine", null, 0, null)
    
  val n1 = Set(country2, country3, country4)
  val n2 = Set(country1, country3, country4)
  val n3 = Set(country1, country2)
  val n4 = Set(country1, country2)
  
  country1.neighboring_countries = n1
  country2.neighboring_countries = n2
  country3.neighboring_countries = n3
  country4.neighboring_countries = n4
  
}