package controller.dto;

public class ProdottoDto {
	
	int idProdotto;
	String nome;
	String imgProdotto; 
	double prezzo;

	// Costruttore di default

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public String getImgProdotto() {
		return imgProdotto;
	}
	public void setImgProdotto(String imgProdotto) {
		this.imgProdotto = imgProdotto;	
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	} 
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	@Override
	public String toString() {
		String stato = "PRODOTTO:\n";	
		stato += "Nome: "+this.getNome()+"\n";		
		stato += "Prezzo: "+this.getPrezzo()+"\n";
		stato += "Foto: "+this.getImgProdotto()+"\n";
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
		ProdottoDto other = (ProdottoDto) obj;
		return idProdotto == other.idProdotto;
	}

	








}
