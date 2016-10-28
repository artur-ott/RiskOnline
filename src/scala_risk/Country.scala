package scala_risk

case class Country(name : String, neighboring_countries : List[Country], 
                   troups : Int, owner : Player) {}