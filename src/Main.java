
public class Main {

    public static void main(String[] args){
        Parser parser = new Parser("https://openmensa.org/api/v2/canteens");
        Data data = new Data(parser, System.out);
        data.initialize();
        Canteen alteMensa = data.getCanteenByID(79);
        data.initializeMeals(alteMensa);
        System.out.println(alteMensa.getMealsToday().get(0).getDetais());
    }
}
