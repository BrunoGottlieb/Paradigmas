package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import application.GithubWithGson;

public class GitHubAnalyzerCmd {

	public static ArrayList<String> urlList = new ArrayList<String>();

	public static void main(String[] args) {
		
		GithubWithGson.guiOrCmd = 1;

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
			GithubWithGson d = new GithubWithGson();
			d.start(s);
		}
		
		System.out.println("\nRESULTADOS:\n");
		
		System.out.println("Maior numero de commits: " + GithubWithGson.maiorRepos +
                "   [" + GithubWithGson.maiorNumCommits + " commits] ");
		
		System.out.println("\nMenor numero de commits: " + GithubWithGson.menorRepos +
                "   [" + GithubWithGson.menorNumCommits + " commits]");
		
		System.out.println("\nRepositorio com commit mais recente: " + GithubWithGson.reposMaisRecente);
		
		System.out.println("\nRepositorio com commit mais antigo: " + GithubWithGson.reposMaisAntigo);
		
	}

}
