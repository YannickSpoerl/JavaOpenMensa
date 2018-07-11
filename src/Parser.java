import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Parser {

    private URL url;

    public Parser(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public void setUrl(String url){
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
}
