package t7_cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GithubWithGsonCMD extends Thread {
	
	public static int maiorNumCommits = 0;
	public static String maiorRepos;
	public static int menorNumCommits = 999;
	public static String menorRepos;
	
	private static Integer tamMedioMensagens = 0;
	private static Integer numCommits = 0;
	
	public static String dataMaisRecente = "0000-00-00";
	public static String horarioDaDataMaisRecente = "00:00:00";
	public static String reposMaisRecente;
	
	public static String dataMaisAntiga = "9999-99-99";
	public static String horarioDaDataMaisAntiga = "99:99:99";
	public static String reposMaisAntigo;
	
	private static boolean proceed = true;
	
	private Integer index = 0;

	public void start(String s) {
		run(s);
	}

	public void run(String a){
		//System.out.println("Github Getter running");
		try {
			while(proceed) {
				//System.out.println("url a ser requisitada: " + a.concat("?page=" + index.toString()));
				gitHubWithGson(a, index);
				index++;
				System.out.println("Valor do index: " + index);
			}
			index = 0;
			proceed = true;
				
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Thread nao pode iniciar");
		}
	}

	public static void gitHubWithGson(String urlRecebida, Integer index) throws IOException {

		String urlstr = urlRecebida.concat("?page=" + index.toString());

		URL url = new URL(urlstr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		System.out.println("Response code: " + con.getResponseCode());

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));

		JsonParser parser = new JsonParser();
		JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
		//System.out.println("Size: "+ results.size()+"\n");
		
		if(results.size() > 0) {
			
			String date = null;
			JsonElement message = null;

			for (JsonElement e : results) {
				date = e
						.getAsJsonObject().get("commit")
						.getAsJsonObject().get("author")
						.getAsJsonObject().get("date").getAsString();

				message = e
						.getAsJsonObject().get("commit")
						.getAsJsonObject().get("message");

				numCommits++;
				tamMedioMensagens += message.toString().length();
				
				compareDate(date, urlRecebida);

			}   

			in.close();
			
		} else {
			
			proceed = false;
			System.out.println("----- Repositorio: " + urlRecebida + " -----");
			System.out.println("Numero de commits: " + numCommits.toString());
			System.out.println("Tamanho medio commits: " + ((Integer)(tamMedioMensagens/numCommits)).toString());
			conferirRecordes(numCommits, urlRecebida);
			
			tamMedioMensagens = 0;
			numCommits = 0;
			
		}
		
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
	
	private static void compareDate(String date, String urlstr) {
		String originalCopia = date;
		String apenasData = date.substring(0, 10);
		String apenasHorario = date.substring(11, 19);
		
		// -------------------------------------------------Comparar recente
		if (apenasData.compareTo(dataMaisRecente) > 0) {
            dataMaisRecente = apenasData;
            horarioDaDataMaisRecente = apenasHorario;
            reposMaisRecente = urlstr;
        } else if (apenasData.compareTo(dataMaisRecente) == 0) {
        	compareTime(apenasHorario, urlstr, apenasData, 1);
        } else if (apenasData.compareTo(dataMaisRecente) < 0){}
        else {
            System.out.println("A data nao foi comparada.");
        }
		
		// -------------------------------------------------Comparar antiga
		if (apenasData.compareTo(dataMaisAntiga) < 0) {
            dataMaisAntiga = apenasData;
            horarioDaDataMaisAntiga = apenasHorario;
            reposMaisAntigo = urlstr;
        } else if (apenasData.compareTo(dataMaisAntiga) == 0) {
        	compareTime(apenasHorario, urlstr, apenasData, 0);
        } else if (apenasData.compareTo(dataMaisAntiga) > 0){}
        else {
            System.out.println("A data nao foi comparada.");
        }
	}
	
	private static void compareTime(String time, String urlstr, String data, int condicao) {
		if (condicao == 1 && time.compareTo(horarioDaDataMaisRecente) > 0) {
            dataMaisRecente = data;
            reposMaisRecente = urlstr;
            horarioDaDataMaisRecente = time;
		}
		
		if (condicao == 0 && time.compareTo(horarioDaDataMaisAntiga) < 0) {
            dataMaisAntiga = data;
            reposMaisAntigo = urlstr;
            horarioDaDataMaisAntiga = time;
		}
	}

}