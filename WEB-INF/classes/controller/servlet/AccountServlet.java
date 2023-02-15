package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.UtenteDto;
import controller.service.UtenteService;
import model.bean.Utente;
import model.exception.NotFoundException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di AccountServlet");
		UtenteService service = new UtenteService();

		HttpSession sessione = request.getSession();			
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");

		ServletContext contesto = this.getServletContext();
		String dest = null;

		Utente utente = new Utente();

		if (utenteSession != null) {	
			try {
				utente = service.cercaUtente(utenteSession.getUsername());
				System.out.println(utente);
				
				request.setAttribute("utente", utenteSession);
				request.setAttribute("utenteBean", utente);							
				dest = "account";

			} catch (NotFoundException e) {			
				e.printStackTrace();
				request.setAttribute("errore", "Errore nella ricerca dell'utente");
				dest = "index";
			} 
		} else {
			request.setAttribute("errore", "Sessione scaduta!");			
			dest = "index";
		}

		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di account");
	}

}
