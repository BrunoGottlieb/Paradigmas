// Java program for Checking Internet connectivity
import java.util.*;
import java.io.*;

public class CheckInternetConnection {
    public static int check() throws Exception {
        Process process = java.lang.Runtime.getRuntime().exec("ping www.random.org");
        int x = process.waitFor();
        if (x == 0) {
            System.out.println("Conexao com a internet encontrada, metodo online\n");
            return 1;
        }
        else {
            System.out.println("Sem conexao com a internet, metodo offline\n");
            return 0;
        }
    }
}
