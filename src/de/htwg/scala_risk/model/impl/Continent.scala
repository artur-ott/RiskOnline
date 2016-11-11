package scala_risk

case class Continent(name : String, countries : Set[Country],
                     ownedBy : Player, bonusTroups : Int) {}