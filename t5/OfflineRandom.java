import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class OfflineRandom{

    ExibirResposta resp = new ExibirResposta();
    int tam = 0;

    public void randMeth(String[] array){
        Random random = new Random();

        for(int i=0; array[i] != null; i++) tam++;

        for(int i=0; array[i] != null; i++) // Embaralha o vetor
        {
            String temp = array[i];
            int randomIndex = random.nextInt(tam);
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }

        resp.respFunc(array);
    }

}
