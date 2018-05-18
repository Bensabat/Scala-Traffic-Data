import collection.mutable.Stack
import org.scalatest._

class CSVClassTest extends FlatSpec with Matchers
{
    "A country object" should "be well created" in
    {
        val line1 = "302687,FR,France,EU,http://en.wikipedia.org/wiki/France,"
        val countryObjet1 = csv_class.Country("302687","FR","France","EU","http://en.wikipedia.org/wiki/France", " ")
        val anyObject1 = csv_class.CSVClass.toAny("country", line1);
        anyObject1 should be (countryObjet1)

        val line2 = "302570,EH,Western Sahara,AF,http://en.wikipedia.org/wiki/Western_Sahara,Sahrawian"
        val countryObjet2 = csv_class.Country("302570","EH","Western Sahara","AF","http://en.wikipedia.org/wiki/Western_Sahara","Sahrawian ")
        val anyObject2 = csv_class.CSVClass.toAny("country", line2);
        anyObject2 should be (countryObjet2)
    }

    "An airport object" should "be well created" in
    {
        val line1 = "6523,00A,heliport,Total Rf Heliport,40.07080078125,-74.93360137939453,11,NA,US,US-PA,Bensalem,no,00A,,00A,,,"
        val airportObjet1 = csv_class.Airport("6523","00A","heliport","Total Rf Heliport","40.07080078125","-74.93360137939453","11","NA","US","US-PA","Bensalem","no","00A","","00A","",""," ")
        val anyObject1 = csv_class.CSVClass.toAny("airport", line1);
        anyObject1 should be (airportObjet1)

        val line2 = "39730,CA-0084,closed,Casey Airport,47.93333435058594,-74.0999984741211,,NA,CA,CA-QC,,no,,,,,,"
        val airportObjet2 = csv_class.Airport("39730","CA-0084","closed","Casey Airport","47.93333435058594","-74.0999984741211","","NA","CA","CA-QC","","no","","","","",""," "
)
        val anyObject2 = csv_class.CSVClass.toAny("airport", line2);
        anyObject2 should be (airportObjet2)
    }

    "A runway object" should "be well created" in
    {
        val line1 = "269408,6523,00A,80,80,ASPH-G,1,0,H1,,,,,,,,,,,"
        val runwayObjet1 = csv_class.Runway("269408","6523","00A","80","80","ASPH-G","1","0","H1","","","","","","","","","",""," ")
        val anyObject1 = csv_class.CSVClass.toAny("runway", line1);
        anyObject1 should be (runwayObjet1)

        val line2 = "252182,6539,00IL,2500,75,TURF-F,0,0,18,,,,,290,36,,,,,350"
        val runwayObjet2 = csv_class.Runway("252182","6539","00IL","2500","75","TURF-F","0","0","18","","","","","290","36","","","","","350 ")
        val anyObject2 = csv_class.CSVClass.toAny("runway", line2);
        anyObject2 should be (runwayObjet2)
    }
}
