package main

import csv_class.Country
import csv_class.Airport
import csv_class.Runway

object Main
{
  // Creating immutable lists for .csv data - Global Parameters
  val countriesList = csv_parser.CSVParser.csvToList("country").asInstanceOf[List[Country]]
  val airportsList  = csv_parser.CSVParser.csvToList("airport").asInstanceOf[List[Airport]]
  val runwaysList   = csv_parser.CSVParser.csvToList("runway").asInstanceOf[List[Runway]]
  
  def main(args: Array[String]): Unit =
  {
    // Pretty title
    println("\n---------------------")
    println(Console.BLUE + "Scala "+ Console.WHITE + "Traffic " + Console.RED + "Data" + Console.RESET)
    println("---------------------\n")

    // Function for the query option
    def query(): Unit =
    {
      println("You selected the query option.\n")
      println("Please, enter a country name or a country code.\n")
      val country_in = utils.Utils.countryNameFormate(scala.io.StdIn.readLine(Console.WHITE + "ScalaAirport> " + Console.RESET))
      println

      (country_in, country_in.length) match
      {
        case ("quit" | "Quit", 4) => println("Thanks for use, good bye!\n");
        case (_, 2) => csv_class.CSVClass.countryToAirportsPrint(country_in, airportsList, runwaysList);
                       query
        case (_, _) => val country_code = utils.Utils.countryToCode(country_in, countriesList, 10); // ex: France => FR
                       csv_class.CSVClass.countryToAirportsPrint(country_code, airportsList, runwaysList);
                       query
      }
    }

    // Function for the report option
    def report(): Unit =
    {
      println("You selected the report option.\n");
      println("Which report do you want?\n")
      println("Press "+ Console.RED + "a" + Console.RESET + ": top 10 countries with hightest number of airports.")
      println("Press "+ Console.RED + "b" + Console.RESET + ": top 10 countries with lowest number of airports.")
      println("Press "+ Console.RED + "c" + Console.RESET + ": type of runways per country.")
      println("Press "+ Console.RED + "d" + Console.RESET + ": top 10 most common runway latitude.\n")
      val report_in = scala.io.StdIn.readLine(Console.WHITE + "ScalaAirport> " + Console.RESET);
      println

      report_in match
      {
        case ("a"|"b") => val isoCountryAirportList = csv_class.CSVClass.isoCountryAirportToList(airportsList)
                                                                        .groupBy(identity)
                                                                        .mapValues(_.size)
                                                                        .toList.map{ case (k, v) => (v, k) }
                                                                        .sorted;
                          report_in match
                          {
                            case ("a") => println("Top 10 countries with hightest number of airports are:\n")
                                          utils.Utils.airportOccPrint(isoCountryAirportList.reverse.splitAt(10)._1)
                            case ("b") => println("Top 10 countries with lowest number of airports are:\n")
                                          utils.Utils.airportOccPrint(isoCountryAirportList.splitAt(10)._1)
                          };
                          report
        case ("c")             => val l1 = csv_class.CSVClass.runwayAirportSurfaceToList(runwaysList)
                                  val l2 = csv_class.CSVClass.airportIsocountryToList(airportsList)
                                  val l3 = l1.flatMap(x => l2.map(y => (x,y)))
                                                .filter{ case ((_,y),(b,_)) => y == b }
                                                .map {case ((x, _),(_,c)) => (x,c) }
                                  report
        case ("d")             => println("Top 10 most commom runway latitude are:\n")
                                  utils.Utils.commomRunwayLatitudePrint;
                                  report
        case ("quit" | "Quit") => println("Thanks for use, good bye!\n");
        case (_)               => report
      }
    }

    // Option parser function
    def optionParser(): Unit =
    {
      if (args.length != 1)
        println("Please, enter --query or --report option.\n")
      else
      {
        val option = args(0)
        option match
        {
          case ("--query"|"-q")  => query
          case ("--report"|"-r") => report
          case (_)               => println("Please, enter --query or --report option.\n")
        }
      }
    }

    // Launching option parser function
    optionParser

    // Closing our buffer
    csv_parser.CSVParser.buffersClosing()
  }
}
