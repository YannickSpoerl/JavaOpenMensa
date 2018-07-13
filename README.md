# Java-Wrapper for the OpenMensa.org API
Makes retrieving data from OpenMensa.org in Java really easy! </br>
## How to use </br>
**The Canteens** </br>
First you need to create a Parser-object. As for now it only works with following URL as parameter: "https://openmensa.org/api/v2/canteens": </br>
`Parser parser = new Parser("https://openmensa.org/api/v2/canteens")` </br>
Further you need to create a new Data-object, with the parser and a PrintStream-object as parameters. The PrintStream is where all 
info-messages will be printed.</br>
I recommend sticking with those two objects all the time, creating multiple parsers and data-objects creating nothing but confusion. </br>
`Data data = new Data(parser, System.out)` </br>
Before doing any requests you need to initialize the Data-object with all Canteens: </br>
Doing `data.initialize()`will initialize all Canteens from the local save-file or fetch them from online and create a new save-file. </br>
If you want to update List of Canteens you can either delete the save-file (allCanteens.txt) or call `data.updateCanteens()` </br>
Now that we retrieved all canteens we can access any of them with multiple functions: </br>
`data.getAllCanteens()` simply returns a Set containing all canteens </br>
`data.getCanteenByName(String name)` returns all Canteens matching the given name </br>
</br>
... to be continued...
