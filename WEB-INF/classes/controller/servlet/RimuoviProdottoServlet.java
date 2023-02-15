package controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.CarrelloDto;
import controller.dto.ProdottoDto;
import controller.service.ProdottoService;
import model.exception.NotFoundException;

@WebServlet("/rimuoviProdotto")
public class RimuoviProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di RimuoviProdottoServlet");
		
		HttpSession sessione = request.getSession();
		CarrelloDto carrello = (CarrelloDto) sessione.getAttribute("carrello");		
		String idProdotto = request.getParameter("idProdotto");
		int id = Integer.parseInt(idProdotto);
		
		ProdottoService service = new ProdottoService();
		
		ArrayList<ProdottoDto> lista = carrello.getListaProdotti();		
		
		try {
			ProdottoDto prodotto = service.covertToDto(id);
			int i =lista.indexOf(prodotto);
			if (i>=0) {
				lista.remove(i);
				carrello.setListaProdotti(lista);
				carrello.setNumProdotti(lista.size());
				double tot = 0;
				for(ProdottoDto p : lista) {
					tot += p.getPrezzo();
				}
				carrello.setTotale(tot);
				sessione.setAttribute("carrello", carrello);				
			}			
			
		} catch (NotFoundException e) {			
			e.printStackTrace();
			e.printStackTrace();		
			request.setAttribute("errore","Prodotto non trovato");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore","Problemi con SQL");	
		}
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/carrello.jsp");
		disp.forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di RimuoviProdottoServlet");
	}

}
