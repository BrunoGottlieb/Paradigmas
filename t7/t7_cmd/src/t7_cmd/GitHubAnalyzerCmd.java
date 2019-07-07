package t7_cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GitHubAnalyzerCmd {

	public static ArrayList<String> urlList = new ArrayList<String>();

	public static void main(String[] args) {

		try{
			File file = new File(args[0]);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				urlList.add(line);
				System.out.println(line);
			}
		}
		catch(Exception e){
			System.out.println("could not find file");
		}

		for (String s : urlList) {
			GithubWithGsonCMD d = new GithubWithGsonCMD();
			d.start(s);
		}
		
		System.out.println("\nRESULTADOS:\n");
		
		System.out.println("Maior numero de commits: " + GithubWithGsonCMD.maiorRepos +
                "   [" + GithubWithGsonCMD.maiorNumCommits + " commits] ");
		
		System.out.println("\nMenor numero de commits: " + GithubWithGsonCMD.menorRepos +
                "   [" + GithubWithGsonCMD.menorNumCommits + " commits]");
		
		System.out.println("\nRepositorio com commit mais recente: " + GithubWithGsonCMD.reposMaisRecente);
		
		System.out.println("\nRepositorio com commit mais antigo: " + GithubWithGsonCMD.reposMaisAntigo);
		
	}

}
