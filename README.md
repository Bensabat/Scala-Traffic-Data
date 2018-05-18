# Scala Traffic Data

Resume
------

This project is about parsing, querying and reporting on large data .csv files with Scala language.

The folder contain:

* a *build.sbt* file
* a main folder containing
     * *.csv* files for airport data
     * scala sources code files
* a test forder for testsuit project

This project is using a library for tests named *ScalaTest*. According to their words, ScalaTest is the most flexible and most popular testing tool in the Scala ecosystem.

___
Execution
---------

Firstly, launch the **sbt console** at the root of the project:

    > sbt

To run all of the test source files, enter *test* from the sbt console with:

    > test

For the execution of the main program, just *run* at the root:

    > run

You will be asked for two options, --query or --report. Note that you can run the program by these following ways:

    > run --query  | > run -q
    > run --report | > run -r

___
Tasks
-----

You can ask for all airports and its associated runways to a given country code or country name.

    > run --query
    > France | > FR

 You can also give a fuzzy name (Bonus Task). For example:

    > frAnCE | > fR

You can give for example a shorter name (Bonus Task). For example:

    > franc | > fran | > fra

And combine the two example behind:

    > fRAn | > fraNC | > FRa

You can ask for a report about the top 10 countries with hightest number of airports.

    > run --report
    > a

You can ask for a report about the top 10 countries with lowest number of airports.

    > run --report
    > b

You can ask for a report about type of runways per country.

    > run --report
    > c

You can ask for a report about the top 10 most common runway latitude.
    
    > run --report
    > d
      
At any time of the program, you can finish your execution by entering *quit* or *Quit*.

    > quit | > Quit

___
Testing
-------

There are some suites tests available on this project that I used along the developpment. You can launch my tests by entering the commande test at the root of the project:

    > test

___
Authors
-------

EPITA School, SCIA Master 1 - Project for Scala course. 

This is my first Scala project.

Authors: **BENSABAT David** (bensab_d)

Please, note that **I worked alone** on this project which is normally a project in a team of two. Ideed, I didn't find a partner.
