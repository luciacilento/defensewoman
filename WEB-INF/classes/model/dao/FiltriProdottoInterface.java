package model.dao;

import java.util.ArrayList;

import model.bean.Prodotto;
import model.exception.NotFoundException;

public interface FiltriProdottoInterface extends ProdottoDaoInterface {
	
	public ArrayList<Prodotto> filtroPrezzi (int prezzo1, int prezzo2) throws NotFoundException;
	public ArrayList<Prodotto> filtroCategoria(int idCategoria) throws NotFoundException;
	
}
