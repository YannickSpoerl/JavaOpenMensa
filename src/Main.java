
public class Main {

    public static void main(String[] args){
        Parser p = new Parser("https://openmensa.org/api/v2/canteens");
        Data d = new Data(p, System.out);
        d.initialize();
        System.out.println(d.getAllCanteens().size());
    }
}
