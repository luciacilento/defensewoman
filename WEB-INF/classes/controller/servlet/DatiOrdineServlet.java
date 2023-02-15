package controller.servlet;

import java.io.IOException;

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
import controller.dto.UtenteDto;
import controller.service.OrdineService;
import controller.service.PagamentoService;
import controller.service.UtenteService;
import model.bean.Ordine;
import model.bean.Pagamento;
import model.bean.Utente;
import model.exception.InvalidInputException;
import model.exception.NotFoundException;

@MultipartConfig
@WebServlet("/datiOrdine")

public class DatiOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di DatiSpedizioneServlet");

		HttpSession sessione = request.getSession();	
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");

		UtenteService service = new UtenteService();
		ServletContext contesto = this.getServletContext();
		String dest = null;

		if (utenteSession != null) {			
			Utente utente = new Utente();			
			try {				
				utente = service.cercaUtente(utenteSession.getUsername());
				System.out.println(utente);

				Ordine ordine = (Ordine) sessione.getAttribute("ordine");
				if (ordine == null) {
					int idOrdine = utente.getFKOrdine();
					if (idOrdine!= 0) {
						OrdineService serviceOrd = new OrdineService();
						ordine = serviceOrd.select(idOrdine);									
					}
				}
				request.setAttribute("ordine", ordine);	
				request.setAttribute("utente", utenteSession);					
				dest = "datiSpedizione";	
			} catch (NotFoundException e) {			
				e.printStackTrace();
				request.setAttribute("errore", "Utente non trovato");
				CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
				request.setAttribute("carrello", carrello);
				dest = "carrello";
			}
		} else {
			request.setAttribute("errore", "Effettuare il login!");
			CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
			request.setAttribute("carrello", carrello);
			dest = "carrello";
		}
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di DatiSpedizioneServlet");	

		String indirizzoSpedizione = request.getParameter("indirizzo");
		String numeroCivico = request.getParameter("numCiv");
		String citta = request.getParameter("citta");
		String provincia = request.getParameter("provincia");
		String nazione = request.getParameter("nazione");
		String note = request.getParameter("note");

		OrdineService serviceOrd = new OrdineService();

		Ordine ordine = new Ordine();
		ordine.setIndirizzoSpedizione(indirizzoSpedizione);
		ordine.setNumeroCivico(numeroCivico);
		ordine.setCitta(citta);
		ordine.setProvincia(provincia);
		ordine.setNazione(nazione);
		ordine.setNote(note);

		ServletContext contesto = this.getServletContext();
		String dest=null;
		HttpSession sessione = request.getSession();	
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");
		if (utenteSession != null) {	
			try {
				serviceOrd.validaOrdine(indirizzoSpedizione, citta, provincia, nazione, numeroCivico);
				UtenteService serviceUte = new UtenteService();

				Utente utente = new Utente();
				utente = serviceUte.cercaUtente(utenteSession.getUsername());
				System.out.println(utente);

				sessione.setAttribute("ordine", ordine);

				int idPagamento = utente.getFKPagamento();
				if (idPagamento!= 0) {
					PagamentoService servicePa = new PagamentoService();
					Pagamento pagamento = servicePa.select(idPagamento);
					System.out.println(pagamento);

					sessione.setAttribute("pagamento", pagamento);
					request.setAttribute("utente", utenteSession);
					request.setAttribute("pagamento", pagamento);						
					dest = "/datiPagamento.jsp";
				} else {					
					request.setAttribute("utente", utenteSession);
					dest = "/datiPagamento.jsp";					
				}	

			} catch (InvalidInputException e) {			
				e.printStackTrace();
				request.setAttribute("errore", "Campi non validi");
				request.setAttribute("ordine", ordine);
				request.setAttribute("utente", utenteSession);
				dest = "/datiSpedizione.jsp";
			} catch (NotFoundException e) {			
				e.printStackTrace();
				request.setAttribute("errore", "Utente non trovato");
				CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
				request.setAttribute("carrello", carrello);
				dest = "/carrello.jsp";
			} 
		} else {
			request.setAttribute("errore", "Sessione scaduta!");
			CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
			request.setAttribute("carrello", carrello);
			dest = "/carrello.jsp";
		}
		RequestDispatcher disp = contesto.getRequestDispatcher(dest);
		disp.forward(request, response);

	}

}
