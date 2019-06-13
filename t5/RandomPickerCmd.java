import java.util.*;
import java.util.ArrayList;

class MainFile{
    public static void main(String[] args) {
        OnlineRandom demoPost = new OnlineRandom();
        OfflineRandom off = new OfflineRandom();
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
        System.out.println("\nConteudo comecara a ser exibido:\n");
        r.readFile();
        r.closeFile();
        System.out.println("\nTerminou de exibir\n");
        System.out.println("\nIniciando embaralhamento:\n");
        switch(metodoEmbaralhamento){
            case 0:
                off.randMeth(r.vetor); // Faz o embaralhamento offline
                break;
            case 1:
                demoPost.onlineRandom(r.vetor); // Faz o embaralhamento online
                break;
            default: System.out.println("Metodo escolhido eh invalido");
                break;
        }
    }
}
