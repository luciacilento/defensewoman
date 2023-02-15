package controller.service;

import model.bean.Ordine;
import model.dao.OrdineDao;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class OrdineService {
	
	public void insert(Ordine mioOrdine) throws InsertErrorException {
		OrdineDao dao = new OrdineDao();
		dao.insert(mioOrdine);		
	}
	
	public void update(Ordine mioOrdine) throws UpdateErrorException {
		OrdineDao dao = new OrdineDao();
	    dao.update(mioOrdine);		
	}
	
	public Ordine select(int idOrdine) throws NotFoundException	{
		OrdineDao dao = new OrdineDao();
		Ordine ordineTrovato = dao.select(idOrdine);
		return ordineTrovato;
	}
	
	public void delete(int idOrdine) throws DeleteErrorException {
		OrdineDao dao = new OrdineDao();
		dao.delete(idOrdine);		
	}
	
	public void validaOrdine(String indirizzo, String citta, String prov, String nazione, String numCiv) throws InvalidInputException {	
		
		if (indirizzo == null || indirizzo.isEmpty() ||
				numCiv == null || numCiv.isEmpty() ||
				citta == null || citta.isEmpty() ||
				prov == null || prov.isEmpty() ||
				nazione == null || nazione.isEmpty()) {
			throw new InvalidInputException();
		} else {
			System.out.println("Validazione OK");
		}					
	}
	
	
	
	
	
	
	
}
