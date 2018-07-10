import java.util.ArrayList;
import java.util.List;

public class Canteen {

    private int id;
    private String name;
    private String city;
    private String adress;
    private double latitude;
    private double longitude;
    private List<Meal> meals;

    public Canteen(int id, String name, String city, String adress, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.city = city;
        this.adress = adress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addMeal(Meal m){
        if(m == null){
            throw new NullPointerException();
        }
        if(meals == null){
            meals = new ArrayList<>();
        }
        meals.add(m);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAdress() {
        return adress;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

