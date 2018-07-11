import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Parser {

    private URL url;

    public Parser(String url) {
        if(url == null){
            throw new NullPointerException();
        }
        if(url.equals("")){
            throw new IllegalArgumentException();
        }
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public void setUrl(String url){
        if(url == null){
            throw new NullPointerException();
        }
        if(url.equals("")){
            throw new IllegalArgumentException();
        }
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public URL getUrl(){
        return this.url;
    }

    private String parseToString(){
        try {
            URLConnection connection = this.url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch(IOException e){
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    private Object parseJSON(){
        String s = parseToString();
        JSONParser p = new JSONParser();
        try {
            return p.parse(s);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getPageCount(){
        try{
            URLConnection connection = this.url.openConnection();
            return Integer.parseInt(connection.getHeaderField("X-Total-Pages"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberOfCanteens(){
        try{
            URLConnection connection = this.url.openConnection();
            return Integer.parseInt(connection.getHeaderField("X-Total-Count"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Canteen> getAllCanteens(){
        List<Canteen> allCanteens = new ArrayList<>();
        JSONArray o = (JSONArray) parseJSON();
        for(Object canteen : o){
            JSONObject newCanteen = (JSONObject) canteen;
            long id = (long) newCanteen.get("id");
            String name = (String) newCanteen.get("name");
            String city = (String) newCanteen.get("city");
            String address = (String) newCanteen.get("address");
            double latitude = 0;
            double longitude = 0;
            try{
                 latitude = (double) ((JSONArray) newCanteen.get("coordinates")).get(0);
                 longitude = (double) ((JSONArray) newCanteen.get("coordinates")).get(1);
            } catch (NullPointerException e){
                System.out.println(name + " coordinates were set to 0, because no coordinates were given.");
            }
            allCanteens.add(new Canteen(id,name, city, address,latitude,longitude));
        }
        return allCanteens;
    }

    public List<Meal> getAllMeals(Canteen canteen){
        URL formerUrl = this.url;
        this.setUrl(this.url.toString() + "/" + canteen.getId() + "/meals");
        JSONArray o = (JSONArray) parseJSON();
        List<Meal> allMealsAllDays = new ArrayList<>();
        for(Object day : o){
            JSONObject dayJSON = (JSONObject) day;
            if(!(boolean) dayJSON.get("closed")){
                List<Meal> allMeals = new ArrayList<>();
                String date = (String) dayJSON.get("date");
                JSONArray meals = (JSONArray) dayJSON.get("meals");
                for(Object m : meals){
                    JSONObject mealJSON = (JSONObject) m;
                    long id = (long) mealJSON.get("id");
                    String name = (String) mealJSON.get("name");
                    String category = (String) mealJSON.get("category");
                    Map<String, Double> prices = new HashMap<>();
                    JSONObject pricesJSON = (JSONObject) mealJSON.get("prices");
                    prices.put("students", (Double) (pricesJSON.get("students")));
                    prices.put("employees", (Double) (pricesJSON).get("employees"));
                    prices.put("pupils", (Double) (pricesJSON).get("pupils"));
                    prices.put("others", (Double) (pricesJSON).get("others"));
                    List<String> notes = new ArrayList<>();
                    JSONArray notesJSON = (JSONArray) mealJSON.get("notes");
                    for(Object note : notesJSON){
                        notes.add((String) note);
                    }
                    Meal newMeal = new Meal(date, id, name, category, prices, notes);
                    allMeals.add(newMeal);
                    canteen.addMeal(newMeal);
                }
                allMealsAllDays.addAll(allMeals);
            }
        }
        this.setUrl(formerUrl.toString());
        return allMealsAllDays;
    }
}
