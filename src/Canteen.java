import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Canteen implements Serializable {

    private long id;
    private String name;
    private String city;
    private String address;
    private double latitude;
    private double longitude;
    private List<Meal> meals;

    public Canteen(long id, String name, String city, String address, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString(){
        return this.name;
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

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

