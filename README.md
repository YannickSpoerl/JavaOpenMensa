# Java-Wrapper for the OpenMensa.org API
Easy access to all canteens and meals from the openmensa.org API in Java! </br>
## How to use </br>
**The Canteens** </br>
First you need to create a Parser-object. As for now it only works with following URL as parameter: "https://openmensa.org/api/v2/canteens": </br>
`Parser parser = new Parser("https://openmensa.org/api/v2/canteens")` </br>
Further you need to create a new Data-object, with the parser and a PrintStream-object as parameters. The PrintStream is where all 
info-messages will be printed.</br>
I recommend sticking with those two objects all the time, using multiple parsers and data-objects create nothing but confusion. </br>
`Data data = new Data(parser, System.out)` </br>
Before doing any requests, you need to initialize the Data-object with all Canteens: </br>
Doing `data.initialize()`will initialize all Canteens from the local save-file or fetch them from online and create a new save-file. </br>
If you want to update List of Canteens you can either delete the save-file (allCanteens.tmp) or call `data.updateCanteens()` </br>
Now that we retrieved all canteens we can access any of them with multiple functions: </br>
* `data.getAllCanteens()` simply returns a Set containing all canteens </br>
* `data.getCanteenByName(String name)` returns all Canteens matching the given name in a set</br>
* `data.getCanteenByCity(String city` returns all Canteens matching the given City </br>
* `data.getCanteenByCoordinates(double latitude, double longitude, double radius)` returns all Canteens within the radius of the given coordinates *(makes new HTTP request)* </br>
* `data.getCanteenByID()` returns the Canteen with the given id if existing </br>
* `data.getPageCount()` returns number of pages of *"https://openmensa.org/api/v2/canteens"* </br>
* `data.getCanteenCount()`returns number of canteens
Note that calling any of those methods will result in an Exception if you haven't initialized data!

**The Meals**</br>
To access any meals of a given canteen you need to initialize those first as well! </br>
You can initialize the meals in 2 ways: </br>
* `data.initializeMeals(Canteen canteen)` initializes the meals of the canteen </br>
* `canteen.initializeMeals(Data data)` also initializes the meals of the canteen </br>
Once the meals of the canteen are initialized, you can access them with following methods: </br>
* `canteen.getMeals()` returns all existing meals of the canteen </br>
* `canteen.getMealsByDate(String date)` or </br>
* `canteen.getMealsByDate(Set<String> dates)` or </br>
* `canteen.getMealsByDate(Date date)` returns a List of all Canteens matching the given date(s) </br>
* `canteen.getMealsToday()` returns a List of todays meals </br>
* `canteen.getMealsByName(String name)` returns a Set of all Meals matching the given name </br>
* `canteen.getMealsById(long id)` returns the Meal matching the given id </br>
* `canteen.getMealsByCategory(String category)` returns a Set of meals matching the given category </br>
* `canteen.getMealsByNote(String note)` or </br>
* `canteen.getMealsByNote(Set<String> notes)` returns a List of meals matching the given note(s) </br>
Note that calling any of those methods will result in an Exception if you haven't initialized the canteens meals!

**General**</br>
The Parser uses JSON-simple, I put the .jar already into the project structure, but you might need to add the .jar manually as well. </br>
Canteen and Meal both contain several Getters and a `toString()`- method. </br>
If any object or data shouldn't exist it will be `null`. </br>
The save-file, where all canteens are stored is called *allCanteens.tmp*. </br>
The code and structure is written in a way, that the number of neccessary HTTP-request is minimal.</br></br>
I'm new to this kind of stuff at all, so there might be some bugs, mistakes, badly written passages, weird structure,... </br>
So feel free to contact me, I would love to extend and improve this project!
