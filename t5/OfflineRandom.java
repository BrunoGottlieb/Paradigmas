import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class OfflineRandom{

    RandomPickerGUI seila = new RandomPickerGUI();

    public static void randMeth(String[] array){
        int tam = 0;

        Random random = new Random();

        for(int i=0; i<array.length; i++)
            System.out.println(array[i]);

        for(int i=0; i<array.length; i++) tam++;

        for(int i=0; i<array.length; i++) // Embaralha o vetor
        {
            String temp = array[i];
            int randomIndex = random.nextInt(tam);
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }

        ExibirResposta.respMeth(array);
    }

    public void testeSecreto(){
        System.out.println("Tamanho do vetor: " + seila.rows.length);
    }

}
