package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DemoParseGithubWithGson extends Thread {
	
	public void run(){
		System.out.println("Github Getter running");
		try {
			gitHubWithGson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Thread nao pode iniciar");
		}
	}
	
	public static void gitHubWithGson() throws IOException {

		//String urlstr = "https://api.github.com/repos/google/gson/commits?page=2";
		String urlstr = "https://api.github.com/repos/google/gson/commits";

		/*if (args.length >= 1) {
			urlstr = args[0];
		}*/

		URL url = new URL(urlstr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		System.out.println("Response code: " + con.getResponseCode());

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));

		// Response header (includes pagination links)
		System.out.println(con.getHeaderFields().get("Link").get(0));

		// Parse a nested JSON response using Gson
		JsonParser parser = new JsonParser();
		JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
		System.out.println("Size: "+ results.size());
		
		JsonElement date = null;
		JsonElement message = null;

		ArrayList<Data> tmp = new ArrayList<>();
		
		for (JsonElement e : results) {
			date = e
			          .getAsJsonObject().get("commit")
			          .getAsJsonObject().get("author")
			          .getAsJsonObject().get("date");
			
			message = e
			          .getAsJsonObject().get("commit")
			          .getAsJsonObject().get("message");
			
			Data c = new Data(date, message);
			
			tmp.add(c);

			System.out.println("Data: " + date);
			System.out.println("Message: " + message);
		    }   

		System.out.println("Everything was ok.");
		in.close();
	}

}

class Data {
	private JsonElement date;
	private JsonElement message;

	public Data(JsonElement date, JsonElement message) {
		this.date = date;
		this.message = message;		
	}
}
