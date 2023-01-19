package yif399;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("deprecation")
public class JSONReadYIF399 {
	
	public static void main(String[] args) {
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(
					new FileReader("./vizsgakYIF399.json")
					);
			
			System.out.println(obj.toJSONString());
			
		} catch(IOException | ParseException e) {
			e.printStackTrace();
		}

	}

}
