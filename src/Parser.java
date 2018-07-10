import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private String parseToString(String u){
        try {
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
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

    private Object parseJSON(String url){
        String s = parseToString(url);
        JSONParser p = new JSONParser();
        try {
            return p.parse(s);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Canteen> getAllCanteens(String url){
        List<Canteen> allCanteens = new ArrayList<>();
        JSONArray o = (JSONArray) parseJSON(url);
        for(Object canteen : o){
            JSONObject newCanteen = (JSONObject) canteen;
            System.out.println(newCanteen.get("name"));
        }
        return null;
    }
}
