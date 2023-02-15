package model.bean;

import java.sql.Blob;
import java.util.Date;

public class Utente {
	
	private int idUtente;
	private String username;
	private String pw;
	private String ruolo;
	private Blob imgProfilo;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String numeroCellulare;
	private String mail;
	private String indirizzo;
	private String numCiv;
	private String citta;
	private String provincia;
	private String cap;
	private String nazione;
	private int FKPagamento;
	private int FKOrdine;
	
	// Costruttore di default
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String password) {
		this.pw = password;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public Blob getImgProfilo() {
		return imgProfilo;
	}
	
	public void setImgProfilo(Blob imgProfilo) {
		this.imgProfilo = imgProfilo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getNumeroCellulare() {
		return numeroCellulare;
	}
	
	public void setNumeroCellulare(String numeroCellulare) {
		this.numeroCellulare = numeroCellulare;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getNumCiv() {
		return numCiv;
	}
	
	public void setNumCiv(String numCiv) {
		this.numCiv = numCiv;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public int getFKPagamento() {
		return FKPagamento;
	}

	public void setFKPagamento(int fKPagamento) {
		FKPagamento = fKPagamento;
	}

	public int getFKOrdine() {
		return FKOrdine;
	}

	public void setFKOrdine(int fKOrdine) {
		FKOrdine = fKOrdine;
	}

	@Override
	public String toString() {
		String stato = "Descrizione Utente:\n";
		stato += "ID: "+this.getIdUtente()+"\n";
		stato += "Username: "+this.getUsername()+"\n";
		stato += "Ruolo: "+this.getRuolo()+"\n";
		stato += "Nome: "+this.getNome()+"\n";
		stato += "Cognome: "+this.getCognome()+"\n";
		stato += "DataNascita: "+this.getDataNascita()+"\n";
		stato += "NumeroCell: "+this.getNumeroCellulare()+"\n";
		stato += "mail: "+this.getMail()+"\n";
		stato += "Indirizzo: "+this.getIndirizzo()+"\n";
		stato += "N° Civico: "+this.getNumCiv()+"\n";
		stato += "Citta: "+this.getCitta()+"\n";
		stato += "CAP: "+this.getCap()+"\n";
		stato += "Provincia: "+this.getProvincia()+"\n";
		stato += "Nazione: "+this.getNazione()+"\n";
		stato += "FKOrdine: "+this.getFKOrdine()+"\n";
		stato += "FKPagamento: "+this.getFKPagamento()+"\n";
		return stato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return idUtente == other.idUtente;
	}
	
}
