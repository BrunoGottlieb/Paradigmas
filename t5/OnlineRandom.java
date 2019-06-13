import java.net.*;
import java.io.*;

public class OnlineRandom{

    String[] resposta = new String[10];
    ExibirResposta resp = new ExibirResposta();

  public void onlineRandom(String[] args) {

  	try {
      String urlstr = "https://www.random.org/lists/?mode=advanced";
      URL url = new URL(urlstr);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("User-Agent", "Mozilla/5.0");
      con.setDoOutput(true);

      //String data = "list=Fulano%0D%0ABeltrano%0D%0ASicrano&format=plain&rnd=new";

      String data = "list=";
      for(int i=0; args[i] != null; i++){
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
      while ((responseLine = in.readLine()) != null) {
        resposta[i] = responseLine;
        response.append(responseLine + "\n");
        i++;
      }
      // Mostra resposta
      resp.respFunc(resposta);

      in.close();
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }
}
