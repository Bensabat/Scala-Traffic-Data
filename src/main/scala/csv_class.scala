package csv_class

// Classes declaration
case class Country(id: String, code: String, name: String, continent: String, wikipedia_link: String, keywords: String)
case class Airport(id: String, ident: String, types: String, name: String, latitude_deg: String, longitude_deg: String,
                    elevation_ft: String, continent: String, iso_country: String, iso_region: String,
                    municipality: String, scheduled_service: String, gps_code: String, iata_code: String,
                    local_code: String, home_link: String, wikipedia_link: String, keywords: String)
case class Runway(id: String, airport_ref: String, airport_ident: String, length_ft: String, width_ft: String,
                    surface: String, lighted: String, closed: String, le_ident: String, le_latitude_deg: String,
                    le_longitude_deg: String, le_elevation_ft: String, le_heading_degT: String,
                    le_displaced_threshold_ft: String, he_ident: String, he_latitude_deg: String, he_longitude_deg: String,
                    he_elevation_ft: String, he_heading_degT: String, he_displaced_threshold_ft: String)

object CSVClass
{
    // Function that return a country/airport/runway object giving a file.csv string line
    def toAny(types: String, line: String) =
    {
      val cols = (line + " ").split(",")
      types match
      {
        case ("country") => Country(cols(0), cols(1), cols(2), cols(3), cols(4), cols(5))
        case ("airport") => Airport(cols(0), cols(1), cols(2), cols(3), cols(4), cols(5), cols(6), cols(7), cols(8), cols(9),
                                    cols(10), cols(11), cols(12), cols(13), cols(14), cols(15), cols(16), cols(17))
        case ("runway")  => Runway (cols(0), cols(1), cols(2), cols(3), cols(4), cols(5), cols(6), cols(7), cols(8), cols(9),
                                    cols(10), cols(11), cols(12), cols(13), cols(14), cols(15), cols(16), cols(17), cols(18),
                                    cols(19))
        case (_)         => ???
      }
    }

    // Function that print runways associated to a given airport
    def airportToRunwaysPrint(airport_ident: String, lines: List[Runway], acc: Int = 0): Unit = lines match
    {
        case (Nil) if acc == 0                             => println("\t--> No runways associated to that airport.\n")
        case (Nil)                                         => println
        case (e::tail) if e.airport_ident == airport_ident => println("\t--> " + e.id
                                                                        + " | " + e.airport_ref
                                                                        + " | " + e.airport_ident
                                                                        + " | " + e.length_ft
                                                                        + " | " + e.width_ft
                                                                        + " | " + e.surface);
                                                            airportToRunwaysPrint(airport_ident, tail, acc + 1)
        case (e::tail)                                     => airportToRunwaysPrint(airport_ident, tail, acc)
    }

    // Function that print airports associated to a country
    def countryToAirportsPrint(country: String, airportsList: List[Airport], runwaysList: List[Runway]): Unit =
    {
      def countryToAirportsPrintHat(airport_list: List[Airport], nb_airport: Int): Unit = airport_list match
      {
        case (Nil)                                                 => println(nb_airport + " airports founded in " + Console.RED + country + Console.RESET + ".\n")
        case (e::tail) if e.iso_country == ("\"" + country + "\"") => println(e.name);
                                                                      print(Console.BLUE);
                                                                      airportToRunwaysPrint(e.ident, runwaysList) // print runways
                                                                      print(Console.RESET)                                                                      
                                                                      countryToAirportsPrintHat(tail, nb_airport + 1);
        case (e::tail)                                             => countryToAirportsPrintHat(tail, nb_airport)
      }
      if (country == "ZZ")
        println(Console.RED + "Error" + Console.RESET + ": Unkown country code.\n")
      else
        countryToAirportsPrintHat(airportsList, 0)
    }

    // Function that return the list containing all the airports iso country
    def isoCountryAirportToList(airportsList: List[Airport], acc: List[String] = Nil)
    : List[String] = airportsList match
    {
        case (Nil)     => acc
        case (e::tail) => isoCountryAirportToList(tail, e.iso_country::acc)
    }
        
    // Function that return the list containing all runway airport associated to its surface
    def runwayAirportSurfaceToList(runwaysList: List[Runway], acc: List[(String, String)] = Nil)
      : List[(String, String)] = runwaysList match
    {
      case (Nil)     => acc
      case (e::tail) => runwayAirportSurfaceToList(tail, acc:+(e.airport_ident, e.surface))
    }

    // Function that return the list containing all airport id associated to its country
    def airportIsocountryToList(airportsList: List[Airport], acc: List[(String, String)] = Nil)
      : List[(String, String)] = airportsList match
    {
      case (Nil)     => acc
      case (e::tail) => airportIsocountryToList(tail, acc:+(e.ident, e.iso_country))
    }
}
