import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;

public class ImageDownload{
    public static void download(String urlToDownload){
        Image image = null;
        try {
            URL url = new URL(urlToDownload);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            FileOutputStream fos = new FileOutputStream(EnadeUFSMExplorer.imageDirectory);
            fos.write(response);
            fos.close();
            System.out.println("\nImagem baixada com sucesso!\n");
        } catch (IOException e) {
            System.out.println("\nNao foi possivel baixar a imagem.\n");
        }
    }
}
