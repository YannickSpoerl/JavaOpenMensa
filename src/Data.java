import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {

    private Parser parser;
    private boolean initialized;
    private int pageCount;
    private int canteenCount;
    private Set<Canteen> allCanteens;
    private PrintStream out;

    public Data(Parser parser, PrintStream out){
        if(parser == null || out == null){
            throw new NullPointerException();
        }
        this.parser = parser;
        this.out = out;
        this.allCanteens = new HashSet<>();
        this.initialized = false;
    }

    public void initializeMeals(Canteen canteen){
        this.out.println("Initializing Meals of " + canteen.getName() + "...");
        canteen.setMeals(parser.getAllMeals(canteen));
    }

    public Set<Canteen> getCanteenByCity(String city){
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        if(city == null){
            throw new NullPointerException();
        }
        if(city.equals("")){
            throw new IllegalArgumentException();
        }
        Set<Canteen> canteensInCity = new HashSet<>();
        for(Canteen canteen : this.allCanteens){
            if(canteen.getCity().equals(city)){
                canteensInCity.add(canteen);
            }
        }
        return canteensInCity;
    }

    public Canteen getCanteenByID(int id){
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        if(id <= 0){
            throw new IllegalArgumentException();
        }
        for(Canteen canteen : this.allCanteens){
            if(canteen.getId() == id){
                return canteen;
            }
        }
        return null;
    }

    public List<Canteen> getCanteenByCoordinates(double latitude, double longitude, double radius){
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        if(radius <= 0){
            throw new IllegalArgumentException();
        }
        String formerURL = this.parser.getUrl().toString();
        this.parser.setUrl("http://openmensa.org/api/v2/canteens?near[lat]=" + latitude + "&near[lng]=" + longitude + "&near[dist]=" + radius);
        List<Canteen> canteensInRadius = this.parser.getAllCanteens();
        this.parser.setUrl(formerURL);
        return canteensInRadius;
    }

    public Set<Canteen> getCanteenByName(String name){
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        if(name == null){
            throw new NullPointerException();
        }
        if(name.equals("")){
            throw new IllegalArgumentException();
        }
        Set<Canteen> canteensWithName = new HashSet<>();
        for(Canteen canteen : this.allCanteens){
            if(canteen.getName().contains(name)){
                canteensWithName.add(canteen);
            }
        }
        return canteensWithName;
    }

    public Set<Canteen> getAllCanteens() {
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        return allCanteens;
    }

    public int getPageCount() {
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        return this.pageCount;
    }

    public int getCanteenCount(){
        if(!this.initialized){
            throw new RuntimeException("You have to initialize Data first, before calling any other methods!");
        }
        return this.canteenCount;
    }

    public void initialize(){
        this.out.println("Initializing all canteens ...");
        File f = new File("allCanteens.txt");
        if(!f.exists()) {
            this.out.println("Fetching canteens from online...");
            updateCanteens();
            writeCanteensToFile();
            this.out.println("Initialized all canteens!");
            this.initialized = true;
            return;
        }
        this.out.println("Fetching canteens from local file");
        this.allCanteens = readCanteensFromFile();
        this.out.println("Initialized all canteens!");
        this.initialized = true;
    }

    private void updateCanteens(){
        this.pageCount = parser.getPageCount();
        this.canteenCount = parser.getNumberOfCanteens();
        String formerURL = this.parser.getUrl().toString();
        this.parser.setUrl(this.parser.getUrl() + "/?page=X");
        for(int i = 1; i <= pageCount; i++){
            String url = this.parser.getUrl().toString();
            url = url.substring(0,url.length()-1);
            url += i;
            this.parser.setUrl(url);
            this.allCanteens.addAll(this.parser.getAllCanteens());
        }
        this.parser.setUrl(formerURL);
    }

    private Set<Canteen> readCanteensFromFile(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("allCanteens.txt"));
            Set<Canteen> canteens = (Set<Canteen>) objectInputStream.readObject();
            objectInputStream.close();
            return canteens;
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    private void writeCanteensToFile(){
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("allCanteens.txt"));
            objectOutputStream.writeObject(this.allCanteens);
            objectOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
