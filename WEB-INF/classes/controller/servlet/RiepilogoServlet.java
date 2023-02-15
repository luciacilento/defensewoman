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
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

@MultipartConfig
@WebServlet("/riepilogo")
public class RiepilogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di DatiPagamentoServlet");

		HttpSession sessione = request.getSession();	
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");
		Pagamento pagamento = (Pagamento) sessione.getAttribute("pagamento");

		PagamentoService servicePag = new PagamentoService();
		UtenteService serviceUte = new UtenteService();
		OrdineService serviceOrd = new OrdineService();
		ServletContext contesto = this.getServletContext();
		String dest = null;		

		if (utenteSession != null) {	
			try {			
				//recupero l'ordine in sessione
				Ordine ordine = (Ordine) sessione.getAttribute("ordine");
				if (pagamento==null) {
					request.setAttribute("utente", utenteSession);
					request.setAttribute("errore", "Pagamento non valido!");
					dest = "datiPagamento";
					
				} else if (ordine==null) {
					request.setAttribute("utente", utenteSession);
					request.setAttribute("errore", "Ordine non valido!");
					dest = "datiSpedizione";
				} else {
					Utente utente = new Utente();
					utente = serviceUte.cercaUtente(utenteSession.getUsername());

					boolean utenteModificato=false;

					int idOrdine = utente.getFKOrdine();
					if (idOrdine!=0) {
						ordine.setIdOrdine(idOrdine);
						serviceOrd.update(ordine);
					} else {
						serviceOrd.insert(ordine);
						utente.setFKOrdine(ordine.getIdOrdine());	
						utenteModificato=true;
					}

					int idPagamento = utente.getFKPagamento();
					if (idPagamento!= 0) {
						pagamento.setIdPagamento(idPagamento);

						servicePag.update(pagamento);

					} else {	
						servicePag.insert(pagamento);
						utente.setFKPagamento(pagamento.getIdPagamento());	
						System.out.println(pagamento.getIdPagamento());
						utenteModificato=true;
					}
					if (utenteModificato) {
						//(condizione) ? true : false
						//serviceUte.modificaProfilo(utente,(utente.getImgProfilo() == null) ? null : utente.getImgProfilo().getBinaryStream(), utente.getUsername());
						serviceUte.insertFKkeys(utente);	
						
						//settare disponibilità prodotto -------------------- CONTROLLA!					
						
					}					
					sessione.removeAttribute("carrello");					
					request.setAttribute("utente", utenteSession);					
					dest = "grazie";
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
				request.setAttribute("errore", "Utente non trovato");				
				dest = "index";
			} catch (UpdateErrorException e) {			
				e.printStackTrace();
				request.setAttribute("utente", utenteSession);
				request.setAttribute("pagamento", pagamento);
				request.setAttribute("errore", "Errore nella modifica dei dati del pagamento");				
				dest = "datiPagamento";
			} catch (InsertErrorException e) {				
				e.printStackTrace();
				request.setAttribute("utente", utenteSession);
				request.setAttribute("pagamento", pagamento);
				request.setAttribute("errore", "Errore nell'inserimento dei dati del pagamento");				
				dest = "datiPagamento";
			} 
		} else {
			request.setAttribute("errore", "Sessione scaduta!");
			CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
			request.setAttribute("carrello", carrello);
			dest = "index";
		}
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di DatiPagamentoServlet");		
	}


}


