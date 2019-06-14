import java.util.*;
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Boolean;

class RandomPickerCmd{
    public static void main(String[] args) {
        OfflineRandom.Interface = 0;
        OnlineRandom.Interface = 0;

        Scanner lerEscolha = new Scanner(System.in);
        int metodoEmbaralhamento = 0;

        ReadFile r = new ReadFile();
        r.openFile(args[0]);
        System.out.println("\nConteudo obtido do arquivo:\n");
        r.readFile();
        r.closeFile();
        System.out.println("\nTerminou de exibir\n");
        System.out.println("\nIniciando embaralhamento:\n");

        int tam = 0;
        for(int i=0; r.vetor[i] != null; i++)
            tam++;

        String[] clone = new String[tam];
        for(int i=0; r.vetor[i] != null; i++)
            clone[i] = r.vetor[i];

        if(clone.length < 2){
            System.out.println("\nA lista deve possuir ao menos 2 nomes\n");
            return;
        }
        System.out.println("\nTeste de conexao sera executado, aguarde um instante...\n");
        try{
            switch(CheckInternetConnection.check()){ // Confere se tem internet
                case 0: OfflineRandom.randMeth(clone); // Faz o embaralhamento offline
                break;
                case 1: OnlineRandom.onlineRandom(clone); // Faz o embaralhamento online
                break;
            }
        }catch(Exception erro){
             return;
        }
    }
}
