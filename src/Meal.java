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
        return this.name;
    }

    public String getDetais(){
        String details = this.name + " " + "(" + this.id + ")\n" + this.category + ", " + this.date + "\nprices: \n";
        for(String key : prices.keySet()){
            details += "   " + key + ": " + prices.get(key) + "\n";
        }
        details += "notes: \n";
        for(String note : this.notes){
            details += "   " + note + "\n";
        }
        return details;
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

    public double getPupilPrice(){
        return this.prices.get("pupil");
    }

    public double getStudentPrice(){
        return this.prices.get("students");
    }

    public double getEmployeePrice(){
        return this.prices.get("employees");
    }

    public double getOtherPrice(){
        return this.prices.get("others");
    }
}
