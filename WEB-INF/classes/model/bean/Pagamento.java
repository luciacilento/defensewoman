package model.bean;

import java.util.Date;

public class Pagamento {

	private int idPagamento;
	private String nomeIntestatario;
	private String cognomeIntestatario;
	private String circuitoCarta;
	private String cartaDiCredito;
	private Date scadenzaCarta;
	private String indirizzoFatturazione;
	private String codiceSicurezza;	

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getNomeIntestatario() {
		return nomeIntestatario;
	}

	public void setNomeIntestatario(String nomeIntestatario) {
		this.nomeIntestatario = nomeIntestatario;
	}

	public String getCognomeIntestatario() {
		return cognomeIntestatario;
	}

	public void setCognomeIntestatario(String cognomeIntestatario) {
		this.cognomeIntestatario = cognomeIntestatario;
	}

	public String getCircuitoCarta() {
		return circuitoCarta;
	}

	public void setCircuitoCarta(String circuitoCarta) {
		this.circuitoCarta = circuitoCarta;
	}

	public String getCartaDiCredito() {
		return cartaDiCredito;
	}

	public String getCartaDiCreditoFor() {
		String cartaDiCreditoFor = null;
		if (cartaDiCredito != null && !cartaDiCredito.isEmpty()) {						 
			//cartaDiCreditoFor = cartaDiCredito.replaceAll("(.{4})", "$1 ").trim();
			cartaDiCreditoFor = "xxxx xxxx xxxx "+cartaDiCredito.substring(cartaDiCredito.length()-4);
		}
		return cartaDiCreditoFor;
	}

	public void setCartaDiCredito(String cartaDiCredito) {
		this.cartaDiCredito = cartaDiCredito;
	}

	public Date getScadenzaCarta() {
		return scadenzaCarta;
	}

	public void setScadenzaCarta(Date scadenzaCarta) {
		this.scadenzaCarta = scadenzaCarta;
	}

	public String getIndirizzoFatturazione() {
		return indirizzoFatturazione;
	}

	public void setIndirizzoFatturazione(String indirizzoFatturazione) {
		this.indirizzoFatturazione = indirizzoFatturazione;
	}

	public String getCodiceSicurezza() {
		return codiceSicurezza;
	}

	public void setCodiceSicurezza(String codiceSicurezza) {
		this.codiceSicurezza = codiceSicurezza;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return idPagamento == other.idPagamento;
	}


	public String toString() {
		String stato = "PAGAMENTO:\n";
		stato += "ID: "+this.getIdPagamento()+"\n";
		stato += "Nome intestatario: " +this.getNomeIntestatario()+"\n";
		stato += "Cognome intestatario: "+this.getCognomeIntestatario()+"\n";
		stato += "Circuito carta: "+this.getCircuitoCarta()+"\n";
		stato += "Carta di credito: "+this.getCartaDiCredito()+"\n";
		stato += "Scadenza della carta: "+this.getScadenzaCarta()+"\n";
		stato += "Indirizzo di fatturazione: "+this.getIndirizzoFatturazione()+"\n";
		stato += "Codice di sicurezza: "+this.getCodiceSicurezza()+"\n";
		return stato;



	}


}
