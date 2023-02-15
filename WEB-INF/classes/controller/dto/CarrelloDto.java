package controller.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarrelloDto {

	private ArrayList<ProdottoDto> listaProdotti;
	int numProdotti;
	double totale;	

	public CarrelloDto() {		
		listaProdotti = new ArrayList <ProdottoDto>();		
	}

	public ArrayList<ProdottoDto> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(ArrayList<ProdottoDto> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public int getNumProdotti() {
		return numProdotti;
	}

	public void setNumProdotti(int numProdotti) {
		this.numProdotti = numProdotti;
	}

	public String getTotaleForm() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(totale);
	}




}

