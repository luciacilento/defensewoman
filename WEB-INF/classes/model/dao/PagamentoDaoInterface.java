package model.dao;

import model.bean.Pagamento;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public interface PagamentoDaoInterface extends DaoInterface<Pagamento> {

	public void insert(Pagamento p) throws InsertErrorException;
	
	public Pagamento select(int idPagamento) throws NotFoundException;
	
	public Pagamento recuperaDatiPagamentoUtente(int idPagamento) throws NotFoundException;
	
	public void update(Pagamento pagamentoDaModificare) throws UpdateErrorException;
	
	
	
}
