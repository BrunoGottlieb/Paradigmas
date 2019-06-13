import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class ReadFile{
    public String[] vetor = new String[10];
    private Scanner x;

    public void openFile(String fileName){
        try{
            String tmp = fileName;
            tmp = tmp.concat(".txt");
            x = new Scanner(new File(tmp));
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
            String b = x.next();
            String c = x.next();

            System.out.printf("%s %s %s\n", a,b,c);

            vetor[i] = a;
            i++;
        }
    }

    public void closeFile(){
        x.close();
    }
}
