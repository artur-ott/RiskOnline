package de.htwg.se.scala_risk.model.impl

object CountryWorksheed {
	"hi"                                      //> res0: String("hi") = hi
	var x = 1                                 //> x  : Int = 1
	x                                         //> res1: Int = 1
	"hi"                                      //> res2: String("hi") = hi
	Countries.country10                       //> res3: de.htwg.se.scala_risk.model.impl.Country = Name: VENEZUELA, Neigbors: 
                                                  //| PERU BRASILIEN , troops: 0, owner: Jaromir
	Countries.country11                       //> res4: de.htwg.se.scala_risk.model.impl.Country = Name: PERU, Neigbors: VENEZ
                                                  //| UELA ARGENTINIEN BRASILIEN , troops: 0, owner: Jaromir
	Countries.country12                       //> res5: de.htwg.se.scala_risk.model.impl.Country = Name: ARGENTINIEN, Neigbors
                                                  //| : PERU BRASILIEN , troops: 0, owner: Jaromir
	Countries.country13                       //> res6: de.htwg.se.scala_risk.model.impl.Country = Name: BRASILIEN, Neigbors: 
                                                  //| VENEZUELA PERU ARGENTINIEN NORDAFRIKA , troops: 0, owner: Jaromir
	Countries.country14                       //> res7: de.htwg.se.scala_risk.model.impl.Country = Name: NORDAFRIKA, Neigbors:
                                                  //|  BRASILIEN ZENTRALAFRIKA OSTAFRIKA AEGYPTEN , troops: 0, owner: Jamal
	Countries.country15                       //> res8: de.htwg.se.scala_risk.model.impl.Country = Name: ZENTRALAFRIKA, Neigbo
                                                  //| rs: NORDAFRIKA SUEDAFRIKA OSTAFRIKA , troops: 0, owner: Jamal
	Countries.country16                       //> res9: de.htwg.se.scala_risk.model.impl.Country = Name: SUEDAFRIKA, Neigbors:
                                                  //|  ZENTRALAFRIKA MADAGASKAR OSTAFRIKA , troops: 0, owner: Jamal
	Countries.country17                       //> res10: de.htwg.se.scala_risk.model.impl.Country = Name: MADAGASKAR, Neigbors
                                                  //| : SUEDAFRIKA OSTAFRIKA , troops: 0, owner: Jaromir
	Countries.country18                       //> res11: de.htwg.se.scala_risk.model.impl.Country = Name: OSTAFRIKA, Neigbors:
                                                  //|  NORDAFRIKA SUEDAFRIKA AEGYPTEN ZENTRALAFRIKA MADAGASKAR , troops: 0, owner:
                                                  //|  Jamal
	Countries.country19                       //> res12: de.htwg.se.scala_risk.model.impl.Country = Name: AEGYPTEN, Neigbors: 
                                                  //| NORDAFRIKA OSTAFRIKA , troops: 0, owner: Jamal
	Players.Player1                           //> res13: de.htwg.se.scala_risk.model.impl.Player = Player(Jaromir,RED)
	
	val Player1 = Player("Pro", Colors.RED)   //> Player1  : de.htwg.se.scala_risk.model.impl.Player = Player(Pro,RED)
	val Player2 = Player("Noob", Colors.BLUE) //> Player2  : de.htwg.se.scala_risk.model.impl.Player = Player(Noob,BLUE)
	Players.PlayerList                        //> res14: List[de.htwg.se.scala_risk.model.impl.Player] = List(Player(Jaromir,R
                                                  //| ED), Player(Jamal,BLUE), Player(Pro,RED), Player(Noob,BLUE))
	
	
	


	
}