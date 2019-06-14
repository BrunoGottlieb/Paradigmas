import java.io.*;
import java.util.*;

public class ReadFile{
    public String[] vetor = new String[50];
    private Scanner x;

    public void openFile(String fileName){
        try{
            if(fileName.contains(".txt"))
                x = new Scanner(new File(fileName));
            else{
                String tmp = fileName;
                tmp = tmp.concat(".txt");
                x = new Scanner(new File(tmp));
            }
        }
        catch(Exception e){
            System.out.println("could not find file");
        }
    }

    public void GetVetor(){
        for(int i=0; vetor[i] != null; i++){
            System.out.println(vetor[i]);
        }
    }

    public void readFile(){
        int i = 0;
        while(x.hasNext()){
            String a = x.next();

            System.out.printf("%s\n", a);

            vetor[i] = a;
            i++;
        }
    }

    public void closeFile(){
        x.close();
    }
}
