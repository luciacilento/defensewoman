package controller.dto;

public class UtenteDto {
	
	private String nome;
	private String ruolo;
	private String imgProfilo;
	private String username;
	
	// Costruttore di default
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}	
	public String getImgProfilo() {
		return imgProfilo;
	}
	public void setImgProfilo(String imgProfilo) {
		this.imgProfilo = imgProfilo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	

	@Override
	public String toString() {
		String stato = "Nome: "+getNome()+"\n";
		stato += "Username: "+getUsername()+"\n";
		stato += "Ruolo: "+getRuolo()+"\n";
		stato += "Foto: "+getImgProfilo()+"\n";
		return stato;
	}

}
