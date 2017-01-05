package de.htwg.se.scala_risk.model
import de.htwg.se.scala_risk.model.impl.Colors._
import de.htwg.se.scala_risk.model.impl.{ Player => ImpPlayer }
import de.htwg.se.scala_risk.model.impl.{ Country => ImpCountry }
import de.htwg.se.scala_risk.model.impl.{ Continent => ImpContinent }
import de.htwg.se.scala_risk.model.impl.Colors
import scala.collection.mutable.ArrayBuffer

/**
 * This object represents the whole world of ScalaRisk.
 * @author Nico Lutz
 */
object World {
  /**
   * This object holds all the countries of ScalaRisk.
   * @author Nico Lutz
   */
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
    private[model] var country10 = ImpCountry("VENEZUELA")
    private[model] var country11 = ImpCountry("PERU")
    private[model] var country12 = ImpCountry("ARGENTINIEN")
    private[model] var country13 = ImpCountry("BRASILIEN")
    private[model] var country14 = ImpCountry("NORDAFRIKA")
    var country15 = ImpCountry("ZENTRALAFRIKA")
    var country16 = ImpCountry("SUEDAFRIKA")
    var country17 = ImpCountry("MADAGASKAR")
    var country18 = ImpCountry("OSTAFRIKA")
    var country19 = ImpCountry("AEGYPTEN")
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
    val n10: Set[Country] = Set(country11, country13)
    val n11: Set[Country] = Set(country10, country12, country13)
    val n12: Set[Country] = Set(country11, country13)
    val n13: Set[Country] = Set(country10, country11, country12, country14)
    val n14: Set[Country] = Set(country13, country15, country18, country19)
    val n15: Set[Country] = Set(country14, country16, country18)
    val n16: Set[Country] = Set(country15, country17, country18)
    val n17: Set[Country] = Set(country16, country18)
    val n18: Set[Country] = Set(country14, country15, country16, country17, country19)
    val n19: Set[Country] = Set(country14, country18)
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
    country10 = country10.copy(neighboring_countries = n10)
    country11 = country11.copy(neighboring_countries = n11)
    country12 = country12.copy(neighboring_countries = n12)
    country13 = country13.copy(neighboring_countries = n13)
    country14 = country14.copy(neighboring_countries = n14)
    country15 = country15.copy(neighboring_countries = n15)
    country16 = country16.copy(neighboring_countries = n16)
    country17 = country17.copy(neighboring_countries = n17)
    country18 = country18.copy(neighboring_countries = n18)
    country19 = country19.copy(neighboring_countries = n19)
    //    country10.neighboring_countries = n10
    //    country11.neighboring_countries = n11
    //    country12.neighboring_countries = n12
    //    country13.neighboring_countries = n13
    //    country14.neighboring_countries = n14
    //    country15.neighboring_countries = n15
    //    country16.neighboring_countries = n16
    //    country17.neighboring_countries = n17
    //    country18.neighboring_countries = n18
    //    country19.neighboring_countries = n19
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

    import scala.collection._
    val listCountries = ArrayBuffer(country10, country11, country12, country13, country14,
      country15, country16, country17, country18, country19)
  }
  /**
   * This object holds all the players of the current game.
   * @author Nico Lutz
   */
  object Players {
    // List to hold the players.
    var playerList: ArrayBuffer[Player] = ArrayBuffer()

    // List to hold the remaining colors.
    var colorList: List[Color] = List(RED, YELLOW, GREEN, BLUE)
    // Default Player e.g. if a country or a continent is not occupied yet.
    val Default = ImpPlayer("", null.asInstanceOf[Color])
    
    var currentPlayer = -1
    /**
     * Function to add Players (defined by (String, String)) to the List
     * and remove the taken colors from colorList. The players name and color
     * are Strings because the controller passes them the way it receives input
     * from the view layer.
     *
     * @param name Name of the player as a String
     * @param color
     */
    def addPlayer(name: String, color: String) /*: String = */ {
      var colorFromString: Color = stringToColor(color)
      if (colorList.contains(colorFromString)) {
        playerList += ImpPlayer(name, colorFromString)
        colorList = colorList.filter { x => x != colorFromString }
      } else {
        println("Color already taken!")
      }
    }
    /**
     * This function transforms a String representation of a color into a player
     * usint stringToColor(color : String) (the color is unique).
     *
     * @param String representation of a color.
     * @return Player associated with the color, default player if
     * color does not exist or is not taken.
     */
    def getPlayerFromColorString(color: String): Player = {
      val playerInList = Players.playerList.filter { x => x.getColor == stringToColor(color) }
      if (playerInList.length == 1) playerInList(0) else Players.Default
    }

    /**
     * This function transforms a String representation of a color into a
     * color of type "Color".
     *
     * @param String representation of a color.
     * @return The corresponding color or an error code if the color does not exist.
     */
    private[Players] def stringToColor(color: String): Color = {
      var colorFromString: Color = null.asInstanceOf[Color]
 
        // Check if the string represents a valid color.
        colorFromString = Colors.withName(color.toUpperCase())

      return colorFromString
    }
    
    def nextPlayer(): Player = {
      currentPlayer += 1
      if (currentPlayer >= playerList.length)
        currentPlayer = 0
      playerList(currentPlayer)
    }
  }

  import de.htwg.se.scala_risk.model.World.Countries
  import de.htwg.se.scala_risk.model.impl.{ Continent => ImpContinent }
  import scala.collection._

  /**
   * This objects holds all the continents of ScalaRisk.
   * @author Nico Lutz
   */
  object Continents {
    val countriesOfContinent1: immutable.Set[Int] = immutable.Set(0, 1, 2, 3)
    val countriesOfContinent2: immutable.Set[Int] = immutable.Set(4, 5, 6, 7, 8, 9);

    val continent1 = ImpContinent("SUEDAMERIKA", countriesOfContinent1, bonusTroops = 2)
    val continent2 = ImpContinent("AFRIKA", countriesOfContinent2, bonusTroops = 3)

    var listContinents: mutable.Buffer[Continent] = mutable.Buffer(continent1, continent2)
  }
}