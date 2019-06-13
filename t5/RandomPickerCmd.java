import java.util.*;
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Boolean;

class RandomPickerCmd{
    public static void main(String[] args) {
        Scanner lerEscolha = new Scanner(System.in);
        int metodoEmbaralhamento = 0;

        System.out.println("\n\nNome do arquivo a ser aberto: " + args[0]);
        System.out.println("\n\nEscolha o metodo de embaralhamento:");
        System.out.println("[0] Offline");
        System.out.println("[1] Online");

        int escolha = lerEscolha.nextInt();
        metodoEmbaralhamento = escolha;

        ReadFile r = new ReadFile();
        r.openFile(args[0]);
        System.out.println("\nConteudo obtido do arquivo:\n");
        r.readFile();
        r.closeFile();
        System.out.println("\nTerminou de exibir\n");
        System.out.println("\nIniciando embaralhamento:\n");


        /*System.out.println("\nChecking internet connection:\n");
        boolean vamosver = isInternetAvailable();
        if(vamosver)
            System.out.println("Voce tem internet!");
            else System.out.println("Voce NAO tem internet!");*/

        int tam = 0;
        for(int i=0; r.vetor[i] != null; i++)
            tam++;

        String[] clone = new String[tam];
        for(int i=0; r.vetor[i] != null; i++)
            clone[i] = r.vetor[i];
        switch(metodoEmbaralhamento){
            case 0:
                OfflineRandom.randMeth(clone); // Faz o embaralhamento offline
                break;
            case 1:
                OnlineRandom.onlineRandom(clone); // Faz o embaralhamento online
                break;
            default: System.out.println("Metodo escolhido eh invalido");
                break;
        }
    }

    public static boolean isInternetAvailable() throws IOException
    {
        return isHostAvailable("google.com") || isHostAvailable("amazon.com")
                || isHostAvailable("facebook.com")|| isHostAvailable("apple.com");
    }

    /*private static boolean isHostAvailable(String hostName) throws IOException
    {
        try(Socket socket = new Socket())
        {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);

            return true;
        }
        catch(UnknownHostException unknownHost)
        {
            return false;
        }
    }*/
}
