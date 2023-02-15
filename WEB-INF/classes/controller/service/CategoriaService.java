package controller.service;

import java.io.InputStream;
import java.util.ArrayList;

import model.bean.Categoria;
import model.dao.CategoriaDao;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;

public class CategoriaService {

	public void inserisciCategoria(Categoria mioCategoria, InputStream foto) throws InsertErrorException  {
		CategoriaDao dao = new CategoriaDao();		
		dao.insert(mioCategoria,foto);
	}

	public Categoria cercaCategoria(int idCategoria) throws NotFoundException {
		CategoriaDao dao = new CategoriaDao();		
		Categoria categoria = dao.select(idCategoria);
		return categoria;
	}
	
	public ArrayList<Categoria> vediCategorie() {
		CategoriaDao dao = new CategoriaDao();		
		ArrayList<Categoria> lista = dao.vediCategorie();
		return lista;		
	}
	
	
	
	
	
	
}
