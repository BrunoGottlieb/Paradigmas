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
	
	public static int maiorNumCommits = 0;
	public static String maiorRepos;
	public static int menorNumCommits = 999;
	public static String menorRepos;

	public void start(String s) {
		run(s);
	}

	public void run(String a){
		System.out.println("Github Getter running");
		try {
			gitHubWithGson(a);		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Thread nao pode iniciar");
		}
	}

	public static void gitHubWithGson(String urlRecebida) throws IOException {

		String urlstr = urlRecebida;

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

		Integer tamMedioMensagens = 0;
		Integer numCommits = 0;

		ArrayList<CommitData> tmp = new ArrayList<>();

		for (JsonElement e : results) {
			date = e
					.getAsJsonObject().get("commit")
					.getAsJsonObject().get("author")
					.getAsJsonObject().get("date");

			message = e
					.getAsJsonObject().get("commit")
					.getAsJsonObject().get("message");

			CommitData c = new CommitData(date, message);

			numCommits++;
			tamMedioMensagens += message.toString().length();

			tmp.add(c);

			System.out.println("Data: " + date);
			System.out.println("Message: " + message);
		}   

		GitHubAnalyzerGUI.data.add(new TableData(urlstr, numCommits.toString(), ((Integer)(tamMedioMensagens/numCommits)).toString()));
		conferirRecordes(numCommits, urlstr);
		
		System.out.println("Everything was ok.");
		in.close();
	}
	
	public static void conferirRecordes(int n, String s) {
		if(n > maiorNumCommits) {
			maiorNumCommits = n;
			maiorRepos = s;
		}	
		if(n < menorNumCommits) {
			menorNumCommits = n;
			menorRepos = s;
		}
			
	}

}

class CommitData {
	private JsonElement date;
	private JsonElement message;

	public CommitData(JsonElement date, JsonElement message) {
		this.date = date;
		this.message = message;		
	}
}
