package de.htwg.se.scala_risk.model.impl

import de.htwg.se.scala_risk.model.World._

object ContinentsWorksheet {
   Continents.countriesOfContinent1               //> res0: scala.collection.immutable.Set[de.htwg.se.scala_risk.model.Country] = 
                                                  //| Set(Name: VENEZUELA, Neigbors: PERU BRASILIEN , troops: 0, owner: , Name: AR
                                                  //| GENTINIEN, Neigbors: PERU BRASILIEN , troops: 0, owner: )
   Continents.listContinents                      //> res1: scala.collection.mutable.Buffer[de.htwg.se.scala_risk.model.Continent]
                                                  //|  = ArrayBuffer(Name: SUEDAMERIKA, containing countries: VENEZUELA ARGENTINIE
                                                  //| N , bonus troops: 2, owner: , Name: AFRIKA, containing countries: PERU , bon
                                                  //| us troops: 3, owner: )
   Players.addPlayer("Petra", "RED")
   Continents.listContinents(0).getIncludedCountries().contains(Countries.listCountries(0))
                                                  //> res2: Boolean = true
   Continents.listContinents(0).getIncludedCountries().foreach { x => x.setOwner(Players.playerList(0)) }
   Continents.listContinents(0).getIncludedCountries()
                                                  //> res3: scala.collection.immutable.Set[de.htwg.se.scala_risk.model.Country] = 
                                                  //| Set(Name: VENEZUELA, Neigbors: PERU BRASILIEN , troops: 0, owner: Petra, Nam
                                                  //| e: ARGENTINIEN, Neigbors: PERU BRASILIEN , troops: 0, owner: Petra)
   Continents.listContinents(0).getIncludedCountries().contains(Countries.listCountries(0))
                                                  //> res4: Boolean = true
   
   

   
}