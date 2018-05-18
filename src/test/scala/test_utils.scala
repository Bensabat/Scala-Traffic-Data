import collection.mutable.Stack
import org.scalatest._

class UtilsClassTest extends FlatSpec with Matchers
{
    "A country name" should "be well formatted" in
    {
        val name1 = utils.Utils.countryNameFormate("FrANcE")
        name1 should be ("France")
        val name2 = utils.Utils.countryNameFormate("ItALIa")
        name2 should be ("Italia")
        val name3 = utils.Utils.countryNameFormate("JaPAN")
        name3 should be ("Japan")
        val name4 = utils.Utils.countryNameFormate("")
        name4 should be ("")
    }

    "A country code" should "be well formatted" in
    {
        val name1 = utils.Utils.countryNameFormate("fR")
        name1 should be ("FR")
        val name2 = utils.Utils.countryNameFormate("It")
        name2 should be ("IT")
        val name3 = utils.Utils.countryNameFormate("jp")
        name3 should be ("JP")
        val name4 = utils.Utils.countryNameFormate("")
        name4 should be ("")
    }

    "A fuzzy country name" should "be well recognized" in
    {
        val name1 = utils.Utils.countryToCode("Franc", main.Main.countriesList)
        name1 should be ("FR")
        val name2 = utils.Utils.countryToCode("Fran", main.Main.countriesList)
        name2 should be ("FR")
        val name3 = utils.Utils.countryToCode("Fra", main.Main.countriesList)
        name3 should be ("FR")
        val name4 = utils.Utils.countryToCode("", main.Main.countriesList)
        name4 should be ("ZZ")
    }

    it should "be well recognized and well formatted" in
    {
        val name1 = utils.Utils.countryToCode(utils.Utils.countryNameFormate("FrANC"), main.Main.countriesList)
        name1 should be ("FR")
        val name2 = utils.Utils.countryToCode(utils.Utils.countryNameFormate("frAn"), main.Main.countriesList)
        name2 should be ("FR")
        val name3 = utils.Utils.countryToCode(utils.Utils.countryNameFormate("FRA"), main.Main.countriesList)
        name3 should be ("FR")
        val name4 = utils.Utils.countryToCode("", main.Main.countriesList)
        name4 should be ("ZZ")
    }

    "Giving a country name" should "return the associated country code" in
    {
        val name1 = utils.Utils.countryToCode("France", main.Main.countriesList)
        name1 should be ("FR")
        val name2 = utils.Utils.countryToCode("Italy", main.Main.countriesList)
        name2 should be ("IT")
        val name3 = utils.Utils.countryToCode("Japan", main.Main.countriesList)
        name3 should be ("JP")
        val name4 = utils.Utils.countryToCode("lol", main.Main.countriesList)
        name4 should be ("ZZ")
        val name5 = utils.Utils.countryToCode("", main.Main.countriesList)
        name5 should be ("ZZ")
    }    
}
