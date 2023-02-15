package model.dao;

import model.exception.DeleteErrorException;


public interface DaoInterface<T> {
	
		
	public void delete (int id) throws DeleteErrorException;
	
}
