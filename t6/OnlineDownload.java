import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;
import java.io.IOException;

public class OnlineDownload{
    public static void download(String onlineURL){
        try (BufferedInputStream in = new BufferedInputStream(new URL(onlineURL).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("enade.csv")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("\nUm novo arquivo foi baixado com sucesso.\n");
        } catch (IOException e) {
            System.out.println("\nNao foi possivel fazer download do arquivo.\n");
        }
    }
}
