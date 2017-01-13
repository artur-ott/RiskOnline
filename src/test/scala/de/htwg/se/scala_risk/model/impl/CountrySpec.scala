package de.htwg.se.scala_risk.model.impl

import de.htwg.se.scala_risk.model.impl.World.Countries
import org.scalatest.Matchers
import org.scalatest.WordSpec
import de.htwg.se.scala_risk.model.{ Country => TCountry }
import de.htwg.se.scala_risk.model.impl.Colors._
import scala.util.control.Breaks._
import de.htwg.se.scala_risk.model.impl.World.Players
class CountrySpec extends WordSpec with Matchers {
  "listCountries" should {
    "contain 10 countries" in {
      Countries.listCountries.length should be(10)
    }
    "contain BRASILIEN" in {
      Countries.listCountries.contains(Countries.country13) should be(true)
    }
  }

  "Every country in the countryList" should {
    "have a name" in {
      var bool: Boolean = true;
      breakable {
        Countries.listCountries.foreach { x => if (x.getName == null) { bool = false; break } }
      }
      bool should be(true)
    }
    "have 0 troops" in {
      var bool: Boolean = true;
      breakable {
        Countries.listCountries.foreach { x => if (x.getTroops != 0) { bool = false; break } }
      }
      bool should be(true)
    }
    "have a set of neighboring countries that is not empty" in {
      var bool: Boolean = true;
      breakable {
        Countries.listCountries.foreach { x =>
          if (!x.getNeighboringCountries.isInstanceOf[Set[TCountry]] ||
            x.getNeighboringCountries.isEmpty) { bool = false; break }
        }
      }
      bool should be(true)
    }
    "be owned by no player (the default player)" in {
      var bool: Boolean = true;
      breakable {
        Countries.listCountries.foreach { x => if (!x.getOwner.equals(Players.Default)) { bool = false; break } }
      }
      bool should be(true)
    }
  }

  "The country ARGENTINIEN" should {
    "have the neighbors: BRASILIEN and PERU" in {
      val country1 = Country("BRASILIEN")
      val country2 = Country("PERU")
      val country3 = Country("ARGENTINIEN")
      val idx3 = Countries.listCountries.indexOf(country3)
      val neighborsOfArgentinien = Countries.listCountries(idx3).getNeighboringCountries
      neighborsOfArgentinien.contains(country1) should be(true)
      neighborsOfArgentinien.contains(country1) should be(true)
    }
  }

  "The country BRASILIEN" should {
    "have ARGENTINIEN as its neighbor" in {
      val country1 = Country("ARGENTINIEN")
      val country2 = Country("BRASILIEN")
      val idx2 = Countries.listCountries.indexOf(country2)
      Countries.listCountries(idx2).getNeighboringCountries.contains(country1) should be(true)
    }
  }

  "After assigning troops to BRASILIEN it" should {
    "contain the troops" in {
      val country = Country("BRASILIEN")
      val idx = Countries.listCountries.indexOf(country)
      Countries.listCountries(idx).setTroops(5)
      val newidx = Countries.listCountries.indexOf(country)
      Countries.listCountries(newidx).getTroops should be(5)
    }
  }

  "When trying to assign a negative number of troops to a country it" should {
    "not affect the number of troops" in {
      val country = Country("BRASILIEN")
      val idx = Countries.listCountries.indexOf(country)
      val troopsBefore = Countries.listCountries(idx).getTroops
      Countries.listCountries(idx).setTroops(-1)
      val newidx = Countries.listCountries.indexOf(country)
      val troopsAfter = Countries.listCountries(newidx).getTroops
      troopsBefore should be(troopsAfter)
    }
  }

  "After assigning a new owner to a country it" should {
    "have the owner set to the new owner" in {
      val country = Country("BRASILIEN")
      val idx = Countries.listCountries.indexOf(country)
      Players.addPlayer("Nico", "RED")
      val player = Players.playerList(0)
      Countries.listCountries(idx).setOwner(player)
      val newidx = Countries.listCountries.indexOf(country)
      Countries.listCountries(newidx).getOwner should be(player)
    }
  }

}