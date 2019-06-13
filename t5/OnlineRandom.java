import java.net.*;
import java.io.*;

public class OnlineRandom{

  public static void onlineRandom(String[] args) {

      String[] resposta = new String[10];

  	try {
      String urlstr = "https://www.random.org/lists/?mode=advanced";
      URL url = new URL(urlstr);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      con.setDoOutput(true);

      String data = "list=";
      for(int i=0; i<args.length; i++){
          data = data.concat(args[i]);
          data = data.concat("%0D%0A");
      }
      data = data.concat("&format=plain&rnd=new");

      // Envia dados pela conexão aberta
      con.getOutputStream().write(data.getBytes("UTF-8"));
      System.out.println("Response code: " + con.getResponseCode());

      // Cria objeto que fará leitura da resposta pela conexão aberta
      BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

      // Lê resposta, linha por linha
      String responseLine;
      StringBuffer response = new StringBuffer();
      int i = 0;
      while (i<args.length) {
        responseLine = in.readLine();
        resposta[i] = responseLine;
        response.append(responseLine + "\n");
        i++;
      }
      int tam = 0;
      for(i=0; resposta[i] != null; i++)
          tam++;
      String[] clone = new String[tam];
      for(i=0; resposta[i] != null; i++)
          clone[i] = resposta[i];
      // Mostra resposta
      ExibirResposta.respMeth(clone);

      in.close();
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }
}
