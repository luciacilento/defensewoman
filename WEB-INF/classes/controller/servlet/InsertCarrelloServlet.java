package controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.CarrelloDto;
import controller.dto.ProdottoDto;
import controller.service.ProdottoService;
import model.exception.NotFoundException;

@MultipartConfig
@WebServlet("/insertCarrello")
public class InsertCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di InsertCarrelloServlet");

		HttpSession sessione = request.getSession();
		CarrelloDto carrello = (CarrelloDto) sessione.getAttribute("carrello");
		String idProdotto = request.getParameter("idProdotto");
		int id = Integer.parseInt(idProdotto);

		ProdottoService service = new ProdottoService();	

		if (carrello == null) {
			CarrelloDto nuovoCarrello = new CarrelloDto();
			try {
				ProdottoDto prodottoDto = service.covertToDto(id);				
				ArrayList<ProdottoDto> lista = nuovoCarrello.getListaProdotti();				
				lista.add(prodottoDto);
				nuovoCarrello.setListaProdotti(lista);
				nuovoCarrello.setNumProdotti(lista.size());
				double tot = 0;
				for(ProdottoDto p : lista) {
					tot += p.getPrezzo();
				}				
				nuovoCarrello.setTotale(tot);
				sessione.setAttribute("carrello", nuovoCarrello);
				request.setAttribute("successo", "Prodotto inserito nel carrello!");

			} catch (NotFoundException e) {			
				e.printStackTrace();		
				request.setAttribute("errore","Prodotto non trovato");			
			} catch (SQLException e) {				
				e.printStackTrace();
				request.setAttribute("errore","Problemi con SQL");	
			}
		} else {		
			try {
				ProdottoDto prodottoDto = service.covertToDto(id);
				ArrayList<ProdottoDto> lista = carrello.getListaProdotti();
				lista.add(prodottoDto);
				carrello.setListaProdotti(lista);				
				carrello.setNumProdotti(lista.size());
				double tot = 0;
				for(ProdottoDto p : lista) {
					tot += p.getPrezzo();
				}
				carrello.setTotale(tot);
				sessione.setAttribute("carrello", carrello);
				request.setAttribute("successo", "Prodotto inserito nel carrello!");

			} catch (NotFoundException e) {			
				e.printStackTrace();		
				request.setAttribute("errore","Prodotto non trovato");			
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errore","Problemi con SQL");	
			}
		}

		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/prodotti.jsp");
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di InsertCarrelloServlet");

	}

}
