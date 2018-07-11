public class Main {

    public static void main(String[] args){
        Parser p = new Parser("https://openmensa.org/api/v2/canteens");
        Data d = new Data(p, System.out);
        d.initialize();
        Canteen alteMensa = d.getCanteenByID(79);
        System.out.println(d.getAllMeals(alteMensa));
    }
}
