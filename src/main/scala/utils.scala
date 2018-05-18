package utils

import csv_class.Country
import csv_class.Airport
import csv_class.Runway

object Utils
{
    // Function that format the given countrie name or country code
    def countryNameFormate(name: String): String = name.length match
    {
      case (2) => name.toUpperCase; // ex: il => IL
      case (_) => name.toLowerCase.capitalize // ex: fRAnCe => France
    }

    // Function that return the associated country code giving a country name
    def countryToCode(country: String, countriesList: List[Country], nbSplit: Int = 15): String =
    {
        def countryToCodeHat(countriesList: List[Country], nbSplit: Int): String = countriesList match
        {
            case (Nil)                                              => "ZZ"
            case (e::tail) if e.name.splitAt(nbSplit)._1 == country => e.code
            case (e::tail)                                          => countryToCodeHat(tail, nbSplit)
        }
        
        val code = countryToCodeHat(countriesList, nbSplit);

        (code, nbSplit == 2) match
        {
            case ("ZZ", false) => countryToCode(country, countriesList, nbSplit - 1)
            case (_, _)        => code
        }
    }

    // Function that print airport with its occurences
    def airportOccPrint(list: List[(Int, String)]): Unit = list match
    {
      case (Nil)     => println()
      case (e::tail) => println("Number of airports in " + Console.RED + e._2 + Console.RESET
                                + " is " + Console.RED + e._1 + Console.RESET);
                        airportOccPrint(tail)
    }

    // Function that retrives all runway latitude in order to know the occurences
    def runwayLatitudeToList(runwaysList: List[Runway], acc: List[String] = Nil)
    : List[String] = runwaysList match
    {
      case (Nil)     => acc
      case (e::tail) => runwayLatitudeToList(tail, e.le_ident::acc)
    }

    // Function that calculate the top 10 most commom runway latitude
    def commomRunwayLatitudePrint(): Unit =
    {
      val occ_list = runwayLatitudeToList(main.Main.runwaysList).groupBy(identity)
                                                      .mapValues(_.size)
                                                      .toList.map{ case (k, v) => (v, k) }
                                                      .sorted.reverse
                                                      .splitAt(10)._1
      def commomRunwayLatitudePrintHat(occ_list: List[(Int, String)]): Unit = occ_list match
      {
        case (Nil)     => println
        case (e::tail) => println(Console.RED + e._2 + Console.RESET + " with " +
                                  Console.RED + e._1 + Console.RESET + " occurrences");
                          commomRunwayLatitudePrintHat(tail)
      }
      commomRunwayLatitudePrintHat(occ_list)
    }
}
