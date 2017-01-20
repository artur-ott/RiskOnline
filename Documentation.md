Documentation
=========================
## The Game
This project contains a fully playable version of the poular strategy game RISK. It can be played locally by 2-6 players. 
It includes load/save functions and logging mechanisms.

## Requirements
* JRE 1.4.0 or higher.

## Programming Language
* Scala
* Java

## Language
* German

## Purpose of the project
* Understanding basic concepts of software engineering including version control systems (GIT), design patterns and ways to develop software (Scrum)

## Project contributers
* Artur Ott, Nico Lutz


## Target group
* Players: Everyone who enjoys strategy games and admirer of the board game
* Developers: This project is open source so feel free to edit the code as you like!

## Getting Started
In the following two ways will be described to download the game. One way for normal players and one way for developers

#### Players
To download and play the game follow these steps:
* Download the folder "Risk" from the git repository
* Double click the ScalaRisk.exe file in this folder

#### Developers
* Clone the git repository
* Import the project into an IDE (e.g. Eclipse) or edit the .scala files in the src folder manually

There are two ways to play the game: by graphical user interface (GUI) or textual user interface (TUI).
To show the TUI on the console just execute the batch.bat file in the folder "Risk". TUI as well as GUI will pop up and run parallel.

## How to play
* Start a new game
* Enter player names and choose a color (at least two players  must be entered)
* Aim is to conquer the world
* Every player has initially 3 troops stationed on his countries (colored in the chosen color)
* Whole continents gain additional troops in the next round (see "Spiel" --> "Zeige Kontinente") depending on their size
* The game works around. One player starts (shown bottom left)
* A players round consists of three steps

###### Rearm
Spread your troops over your countries

######Attack
Attack as much enemy countries as you want.
Dices are rolled according to the troops stationed on each of the two countries (attacker: max. 3 dices, defender: max. 2 dices)
Higher dices beat lower ones and result in troop loss. If two dices are equal the defender wins.

###### Move
 At the end of your round you have the chance to move troops from one of your countries in another country.


## Goal of the game
* Conquer the whole world!

## Restrictions
In opposition to the board game currently some restrictions apply:
* No cards
* You can move troops in the last step as often as you want
* You can move troops to countries that are not neighboring countries of the selected country

## Future improvements
* Eliminate the restrictions
* Add more nice dialogs to guide the user through the game
* Add more maps
* Add different resolutions (and place buttons etc. relative to this)
* Add button to change music
* Add bonus troops to continent overview image
* ...

## Notes
* "save" folder must always be located in the same directory as the .exe or .jar file
How to create a runnable .exe from the scala project:
* Add the following code to your build.sbt:
```
import com.github.retronym.SbtOneJar._

oneJarSettings

// or if using sbt version < 0.13
// seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"
```
* Add the following code to project/plugins.sbt:
```
addSbtPlugin("org.scala-sbt.plugins" % "sbt-onejar" % "0.8")
```
* run sbt one-jar in the command line 
* .jar file located in target/scala-2.11/<name>.jar
* extract .jar file into an empty folder
* add "images" and "music" folder
* add log4j.properties file
* add "log4j-1.2.17.jar" into "lib" (download if necessary)
* select all files --> send to --> zip
* convert rename .zip to .jar
* enjoy

