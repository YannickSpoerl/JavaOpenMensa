import java.util.List;
import java.util.Map;

public class Meal {

    private String date;
    private long id;
    private String name;
    private String category;
    private Map<String, Double> prices;
    private List<String> notes;

    public Meal(String date, long id, String name, String category, Map<String, Double> prices, List<String> notes){
        this.date = date;
        this.id = id;
        this.name = name;
        this.category = category;
        this.prices = prices;
        this.notes = notes;
    }

    public String toString(){
        return this.date + ": " + this.name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public List<String> getNotes() {
        return notes;
    }

    public String getDate() {
        return date;
    }
}
