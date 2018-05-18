package csv_parser

object CSVParser
{
    // Path for .csv files
    val sourcesPath = "./src/main/ressources/"

    // Open .csv files
    val bufferedSourceCountries = io.Source.fromFile(sourcesPath + "countries.csv")
    val bufferedSourceAirports = io.Source.fromFile(sourcesPath + "airports.csv")
    val bufferedSourceRunways = io.Source.fromFile(sourcesPath + "runways.csv")

    // Get data on List of String | Constructing case class later
    val linesCountries = bufferedSourceCountries.getLines.toList
    val linesAirports = bufferedSourceAirports.getLines.toList
    val linesRunways = bufferedSourceRunways.getLines.toList
    
    // Function that fill list with country/airport/runway
    def csvToList(types: String): List[Any] =
    {
      def csv_to_list_hat(lines: List[String], acc: List[Any] = Nil): List[Any] = lines match
      {
        case (Nil)     => acc
        case (e::tail) => val anyObject = csv_class.CSVClass.toAny(types, e);
                          csv_to_list_hat(tail, anyObject::acc)
      }
      types match
      {
        case ("country") => csv_to_list_hat(csv_parser.CSVParser.linesCountries)
        case ("airport") => csv_to_list_hat(csv_parser.CSVParser.linesAirports)
        case ("runway")  => csv_to_list_hat(csv_parser.CSVParser.linesRunways)
        case (_)         => ???
      }
    }

    def buffersClosing(): Unit =
    {
        // Closing our buffer
        bufferedSourceCountries.close
        bufferedSourceAirports.close
        bufferedSourceRunways.close
    }
}
