public class ExibirResposta{

    RandomPickerGUI gui = new RandomPickerGUI();

    public static void respMeth(String[] resposta){
        int i = 0;
        int tam = resposta.length;
        System.out.println("\nPRINTANDO DEPOIS DE EMBARALHADO:\n");
        while(i < tam){
            System.out.println(resposta[i]);
            System.out.println("\nPress Enter Key To Next Name...");
            new java.util.Scanner(System.in).nextLine();
            i++;
        }
        System.out.println("Acabou");
    }
}
