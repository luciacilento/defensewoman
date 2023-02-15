package controller.servlet;

import java.io.IOException;
import java.util.Date;

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
import controller.service.PagamentoService;
import model.bean.Ordine;
import model.bean.Pagamento;
import model.exception.InvalidInputException;

@MultipartConfig
@WebServlet("/datiPagamento")

public class DatiPagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di DatiPagamentoServlet");
		
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/datiPagamento.jsp");
		disp.forward(request, response);
		

	}
	
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di DatiPagamentoServlet");

		HttpSession sessione = request.getSession();	
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");		

		PagamentoService servicePag = new PagamentoService();	
		ServletContext contesto = this.getServletContext();
		String dest = null;

		String nomeIntestatario = request.getParameter("nomeIntestatario");
		String cognomeIntestatario = request.getParameter("cognomeIntestatario");
		String circuitoCarta = request.getParameter("circuitoCarta");
		String cartaDiCredito = request.getParameter("cartaDiCredito");
		String indirizzoFatturazione = request.getParameter("indirizzoFatturazione");
		String codiceSicurezza =request.getParameter("codiceSicurezza");

		Pagamento pagamento = new Pagamento();

		pagamento.setNomeIntestatario(nomeIntestatario);
		pagamento.setCognomeIntestatario(cognomeIntestatario);
		pagamento.setCircuitoCarta(circuitoCarta);
		if (!cartaDiCredito.isEmpty()) {
			pagamento.setCartaDiCredito(cartaDiCredito.replaceAll(" ",""));
		} else {
			Pagamento pagamentoSess = (Pagamento) sessione.getAttribute("pagamento");
			pagamento.setCartaDiCredito(pagamentoSess.getCartaDiCredito());
		}
		pagamento.setIndirizzoFatturazione(indirizzoFatturazione);
		pagamento.setCodiceSicurezza(codiceSicurezza);		

		String mese = request.getParameter("meseScadenza");
		String anno = request.getParameter("annoScadenza"); 	

		if (utenteSession != null) {	
			try {
				Date scadenzaCarta = new Date();
				scadenzaCarta.setYear(Integer.parseInt(anno)-1900);	
				scadenzaCarta.setMonth(Integer.parseInt(mese));
				scadenzaCarta.setDate(1);
				pagamento.setScadenzaCarta(scadenzaCarta);			

				//passo direttamente l'oggetto?
				servicePag.validaPagamento(nomeIntestatario, cognomeIntestatario, circuitoCarta, pagamento.getCartaDiCredito(), codiceSicurezza, scadenzaCarta);

				//recupero l'ordine in sessione
				Ordine ordine = (Ordine) sessione.getAttribute("ordine");

				if (ordine==null) {
					request.setAttribute("utente", utenteSession);
					request.setAttribute("errore", "Ordine non valido!");
					dest = "datiSpedizione";
				} else {
					sessione.setAttribute("pagamento", pagamento);
					CarrelloDto carrello=(CarrelloDto) sessione.getAttribute("carrello");
					request.setAttribute("carrello", carrello);
					request.setAttribute("ordine", ordine);
					request.setAttribute("pagamento", pagamento);
					request.setAttribute("utente", utenteSession);					
					dest = "checkout";
				}
			} catch (InvalidInputException e) {			
				e.printStackTrace();
				request.setAttribute("pagamento", pagamento);
				request.setAttribute("utente", utenteSession);
				request.setAttribute("errore", "Carta di credito scaduta");
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

}
