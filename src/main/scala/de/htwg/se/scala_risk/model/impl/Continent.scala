package de.htwg.se.scala_risk.model.impl
case class Continent(name: String, countries: Set[Country],
  var ownedBy: Player, bonusTroups: Int) {}

//import Countries._
//object Continents {
//  val countriesOfContinent1 = Set(country10, country11, country12, country13)
//  val countriesOfContinent2 = Set(country14, country15, country16, country17, country18, country19);
//  
//  val continent1 = Continent("SUEDAMERIKA", countriesOfContinent1, null, 2) 
//  val continent2 = Continent("AFRIKA", countriesOfContinent2, null, 3)
//}