package model.dao;

import java.io.InputStream;
import java.sql.Blob;

import model.bean.Utente;
import model.exception.FileNotFoundException;
import model.exception.InsertErrorException;
import model.exception.LoginKOException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;
import model.exception.UploadFileException;

public interface UtenteDaoInterface extends DaoInterface<Utente> {
	
	public void insert(Utente t,InputStream foto) throws InsertErrorException;
	public Utente select(String username) throws NotFoundException;
	public void update(Utente utente, InputStream foto, String username1) throws UpdateErrorException;
	public Utente login(String utente, String psw) throws LoginKOException;
	public Blob recuperaFoto(int id) throws FileNotFoundException;
	public int caricaFoto(InputStream foto) throws UploadFileException;
	 

}
