package yif399;

import org.json.simple.JSONObject;


@SuppressWarnings("deprecation")
public class ObjectYIF399 {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("Nev", "Toth David Oliver");
		obj.put("Fizetes", "100000");
		obj.put("Kor", "23");
		System.out.println(obj.toJSONString());
		
		
	}

}
