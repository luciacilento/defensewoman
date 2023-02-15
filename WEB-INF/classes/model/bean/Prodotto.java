package model.bean;

import java.sql.Blob;

public class Prodotto {
	
	private int idProdotto;
	private int disponibilita;
	private String nome;
	private String descrizione;
	private double prezzo;
	private Blob imgProdotto;
	private int FKCategoria;
	private int FKUtente;
	
	
	public int getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public int getDisponibilita() {
		return disponibilita;
	}
	
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}	
	
	public Blob getImgProdotto() {
		return imgProdotto;
	}
	
	public void setImgProdotto(Blob imgProdotto) {
		this.imgProdotto = imgProdotto;
	}
	public int getFKCategoria() {
		return FKCategoria;
	}

	public void setFKCategoria(int fKCategoria) {
		FKCategoria = fKCategoria;
	}	
	
	public int getFKUtente() {
		return FKUtente;
	}

	public void setFKUtente(int fKUtente) {
		FKUtente = fKUtente;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		return idProdotto == other.idProdotto;
	}
	
	
	public String toString() {
		String stato = "PRODOTTO:\n";
		stato += "ID: "+this.getIdProdotto()+"\n";
		stato += "Disponibilità: "+this.getDisponibilita()+"\n";
		stato += "Nome: "+this.getNome()+"\n";
		stato += "Descrizione: "+this.getDescrizione()+"\n";
		stato += "Prezzo: "+this.getPrezzo()+"\n";
		stato += "Foto: "+this.getImgProdotto()+"\n";
		return stato;	
	}



}