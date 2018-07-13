import java.util.*;

public class ExampleMain {

    public static void main(String[] args){
        Parser parser = new Parser("https://openmensa.org/api/v2/canteens"); //create Parser
        Data data = new Data(parser, System.out); //create Data
        data.initialize(); // initialize Data
        Canteen alteMensa = data.getCanteenByID(79); //got a certain canteen
        data.initializeMeals(alteMensa); // initialized alteMensas meals
        Set<Canteen> dresdenCanteens = data.getCanteenByCity("Dresden"); //got some canteens
        for(Canteen c : dresdenCanteens){
            c.initializeMeals(data); //initialize all canteens in dresdenCanteens
        }
        List<Meal> alteMensaToday = alteMensa.getMealsToday(); //got all of todays meals in alteMensa
        Map<Canteen,List<Meal>> auflaeufe = new HashMap<>();
        for(Canteen c : dresdenCanteens){
            auflaeufe.put(c, c.getMealsByCategory("Auflauf")); //got all canteens with their corresponding "Auflauf"-meal
        }
    }
}