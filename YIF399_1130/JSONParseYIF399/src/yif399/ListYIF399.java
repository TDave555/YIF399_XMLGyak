package yif399;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JsonArray;

public class ListYIF399 {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("Tóth Dávid Olivér");
		list.add("100000");
		list.add("23");
		JsonArray jsonarr = new JsonArray(list);
		System.out.println(jsonarr.toString());

	}

}
