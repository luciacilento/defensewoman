package model.dao;

import model.bean.Ordine;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public interface OrdineDaoInterface extends DaoInterface<Ordine> {

	public void insert (Ordine ordineDaInserire)throws InsertErrorException;
	public void update(Ordine ordineDaModificare) throws UpdateErrorException;	
	public Ordine select (int id) throws NotFoundException;
	
	
}
