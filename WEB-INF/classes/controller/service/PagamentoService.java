package controller.service;

import java.util.Date;

import model.bean.Pagamento;
import model.dao.PagamentoDao;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class PagamentoService {

	public void insert(Pagamento mioPagamento) throws InsertErrorException {
		PagamentoDao dao = new PagamentoDao();
		dao.insert(mioPagamento);		
	}	

	public void update(Pagamento mioPagamento) throws UpdateErrorException {
		PagamentoDao dao = new PagamentoDao();
		dao.update(mioPagamento);		
	}

	public Pagamento select(int idPagamento) throws NotFoundException {
		PagamentoDao dao = new PagamentoDao();
		Pagamento pagamentoTrovato = dao.select(idPagamento);
		return pagamentoTrovato;
	}

	public void delete(int idPagamento) throws DeleteErrorException	{
		PagamentoDao dao = new PagamentoDao();
		dao.delete(idPagamento);		
	}

	public void validaPagamento(String nomeIntestatario, String cognomeIntestatario, String circuitoCarta, String cartaDiCredito, String codiceSicurezza, Date scadenzaCarta) throws InvalidInputException {
		if (nomeIntestatario != null && !nomeIntestatario.isEmpty() && 
				cognomeIntestatario != null && !cognomeIntestatario.isEmpty() && 
				circuitoCarta != null && !circuitoCarta.isEmpty() && 
				cartaDiCredito != null && !cartaDiCredito.isEmpty() && 
				codiceSicurezza != null && !codiceSicurezza.isEmpty() &&
				scadenzaCarta != null && scadenzaCarta.after(new Date())) {
			System.out.println("Validazione OK");
		} else {
			throw new InvalidInputException();
		}
	}




}
