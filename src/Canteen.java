import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Canteen implements Serializable {

    private long id;
    private String name;
    private String city;
    private String address;
    private double latitude;
    private double longitude;
    private boolean initializedMeals;
    private List<Meal> meals;

    public Canteen(long id, String name, String city, String address, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.initializedMeals = false;
    }

    public String toString(){
        return this.name;
    }

    public Meal getMealById(long id){
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
        if(id <= 0){
            throw new IllegalArgumentException();
        }
        for(Meal m : this.meals){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public Set<Meal> getMealsByName(String name){
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
        if(name == null){
            throw new NullPointerException();
        }
        if(name.equals("")){
            throw new IllegalArgumentException();
        }
        Set<Meal> mealWithName = new HashSet<>();
        for(Meal m : this.meals){
            if(m.getName().contains(name)){
                mealWithName.add(m);
            }
        }
        return mealWithName;
    }

    public List<Meal> getMealsToday(){
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime today = LocalDateTime.now();
        String date = dateTimeFormatter.format(today);
        List<Meal> todaysMeals = new ArrayList<>();
        for(Meal m : this.meals){
            if(date.equals(m.getDate())){
                todaysMeals.add(m);
            }
        }
        return todaysMeals;
    }

    public List<Meal> getMealsByDate(Set<String> dates){
        if(dates == null){
            throw new NullPointerException();
        }
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
        List<Meal> datesMeals = new ArrayList<>();
        for(Meal m : this.meals){
            for(String date : dates){
                if(m.getDate().equals(date)){
                    datesMeals.add(m);
                }
            }
        }
        return datesMeals;
    }

    public List<Meal> getMealsByDate(String date){
        if(date == null){
            throw new NullPointerException();
        }
        if(date.equals("")){
            throw new IllegalArgumentException();
        }
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
        List<Meal> datesMeals = new ArrayList<>();
        for(Meal m : this.meals){
            if(m.getDate().equals(date)){
                datesMeals.add(m);
            }
        }
        return datesMeals;
    }

    public void initializeMeals(Data data){
        data.initializeMeals(this);
        this.initializedMeals = true;
    }

    public void setMeals(List<Meal> allMeals){
        this.meals = allMeals;
        this.initializedMeals = true;
    }

    public List<Meal> getMeals() {
        if(!initializedMeals){
            throw new RuntimeException("You should initialize the meals before accessing them!");
        }
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

