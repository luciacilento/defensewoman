package model.dao;

import java.io.InputStream;
import java.util.ArrayList;

import model.bean.Categoria;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;


public interface CategoriaDaoInterface extends DaoInterface<Categoria> {	

	public void insert(Categoria t,InputStream foto) throws InsertErrorException;
	public Categoria select (int idCategoria) throws NotFoundException;
	public void update(Categoria aggiornaCategoria, InputStream foto) throws UpdateErrorException;	
	public ArrayList<Categoria> vediCategorie();

}
