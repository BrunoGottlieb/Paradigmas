package application;

import javafx.beans.property.SimpleStringProperty;

public class TableData {

	private SimpleStringProperty repositorio;
	private SimpleStringProperty numeroCommits;
	private SimpleStringProperty tamMessage;

	TableData(String repo, String com, String mes) {
		this.repositorio =  new SimpleStringProperty(repo);
		this.numeroCommits = new SimpleStringProperty(com);
		this.tamMessage = new SimpleStringProperty(mes);
	}

	public SimpleStringProperty repositorioProperty() {
		return repositorio;
	}
	public String getRepositorio() {
		return repositorio.get();
	}
	public void setRepositorio(String f) {
		repositorio.set(f);
	}
	public SimpleStringProperty numeroCommitsProperty() {
		return numeroCommits;
	}
	public String getNumeroCommits() {
		return numeroCommits.get();
	}
	public void setNumeroCommits(String s) {
		numeroCommits.set(s);
	}
	public SimpleStringProperty tamMessageProperty() {
		return tamMessage;
	}
	public String getTamMessage() {
		return tamMessage.get();
	}
	public void setTamMessage(String t) {
		tamMessage.set(t);
	}

}
