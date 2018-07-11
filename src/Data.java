import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class Data {

    private Parser parser;

    private int pageCount;
    private int canteenCount;
    private Set<Canteen> allCanteens;
    private PrintStream out;

    public Data(Parser parser, PrintStream out){
        this.parser = parser;
        this.out = out;
        this.allCanteens = new HashSet<>();
    }

    public Set<Canteen> getAllCanteens() {
        return allCanteens;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public int getCanteenCount(){
        return this.canteenCount;
    }

    public void initialize(){
        this.out.println("Initializing all canteens ...");
        this.pageCount = parser.getPageCount();
        this.canteenCount = parser.getNumberOfCanteens();
        this.parser.setUrl(this.parser.getUrl() + "/?page=X");
        for(int i = 1; i <= pageCount; i++){
            String url = this.parser.getUrl().toString();
            url = url.substring(0,url.length()-1);
            url += i;
            this.parser.setUrl(url);
            allCanteens.addAll(this.parser.getAllCanteens());
        }
        this.out.println("Initialized all canteens!");
    }
}
