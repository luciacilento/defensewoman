package model.bean;

public class Ordine {

	private int idOrdine;
	private String indirizzoSpedizione;
	private String numeroCivico;
	private String citta;
	private String provincia;
	private String nazione;
	private String note;	
	
	// costruttore di defaul
	
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	public String getNumeroCivico() {
		return numeroCivico;
	}
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
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
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return idOrdine == other.idOrdine;
	}
	
	public String toString() {
		String stato = "ORDINE:\n";
		stato += "ID: "+this.getIdOrdine()+"\n";
		stato += "Indirizzo spedizione: "+this.getIndirizzoSpedizione()+"\n";
		stato += "Numero civico: "+this.getNumeroCivico()+"\n";
		stato += "Città: "+this.getCitta()+"\n";
		stato += "Provincia: "+this.getProvincia()+"\n";
		stato += "Nazione: "+this.getNazione()+"\n";
		return stato;

	
	
	
	}
}
