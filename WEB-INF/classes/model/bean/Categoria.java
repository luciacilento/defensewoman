package model.bean;

import java.sql.Blob;

public class Categoria {
	
	private int idCategoria;
	private String nomeCategoria;
	private String descrizioneCategoria;
	private Blob imgCategoria;
	
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getDescrizioneCategoria() {
		return descrizioneCategoria;
	}
	public void setDescrizioneCategoria(String descrizioneCategoria) {
		this.descrizioneCategoria = descrizioneCategoria;
	}
	public Blob getImgCategoria() {
		return imgCategoria;
	}
	public void setImgCategoria(Blob imgCategoria) {
		this.imgCategoria = imgCategoria;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return idCategoria == other.idCategoria;
	}
	
	
	public String toString() {
		String stato = "CATEGORIA:\n";
		stato += "ID: "+this.getIdCategoria()+"\n";
		stato += "Nome: "+this.getNomeCategoria()+"\n";
		stato += "Descrizione: "+this.getDescrizioneCategoria()+"\n";
		stato += "Immagine: "+this.getImgCategoria()+"\n";
		return stato;
	
	
	
	
	
	}
}
