package model.dao;

import java.io.InputStream;
import java.util.ArrayList;

import model.bean.Prodotto;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public interface ProdottoDaoInterface extends DaoInterface<Prodotto> {
	
	public void insert(Prodotto t,InputStream foto) throws InsertErrorException;
	public Prodotto select(int idProdotto) throws NotFoundException;
	public void update (Prodotto t,InputStream foto) throws UpdateErrorException;
	public ArrayList<Prodotto> vediProdotti ();	
	public ArrayList<Prodotto> filtroPrezzi(int prezzo1, int prezzo2) throws NotFoundException;
	public ArrayList<Prodotto> filtroCategoria(int idCategoria) throws NotFoundException;
	public ArrayList<Prodotto> filtroPrezzoMin(int prezzo1) throws NotFoundException;
	public ArrayList<Prodotto> filtroPrezzoCategoria(int idCategoria, int prezzo1, int prezzo2) throws NotFoundException;
	
}
