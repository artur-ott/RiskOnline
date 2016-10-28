package scala_risk

object CountryWorksheed {
  val country = new Country()                     //> java.lang.NoClassDefFoundError: scala_risk/Country
                                                  //| 	at scala_risk.CountryWorksheed$$anonfun$main$1.apply$mcV$sp(scala_risk.C
                                                  //| ountryWorksheed.scala:4)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at scala_risk.CountryWorksheed$.main(scala_risk.CountryWorksheed.scala:3
                                                  //| )
                                                  //| 	at scala_risk.CountryWorksheed.main(scala_risk.CountryWorksheed.scala)
                                                  //| Caused by: java.lang.ClassNotFoundException: scala_risk.Country
                                                  //| 	at java.net.URLClassLoader.findClass(Unknown Source)
                                                  //| 	at java.lang.ClassLoader.loadClass(Unknown Source)
                                                  //| 	at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
                                                  //| 	at java.lang.ClassLoader.loadClass(Unknown Source)
                                                  //| 	... 6 more
  print(country)
  
  
}