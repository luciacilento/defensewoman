package controller.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dto.UtenteDto;
import controller.service.UtenteService;
import model.exception.FileNotFoundException;
import model.exception.LoginKOException;

@MultipartConfig
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet");	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di LoginServlet");
		
		UtenteService service = new UtenteService();	
		
		// recupero i parametri
		String username = request.getParameter("user");
		String password = request.getParameter("psw");		
		
		// avvio sessione con login dell'utente
		try {			
			UtenteDto utente = service.login(username, password);	
			System.out.println(utente);		
			
			HttpSession sessione = request.getSession();			
			sessione.setAttribute("utente", utente);	
			
		} catch (LoginKOException e) {
			System.err.println("Login KO");
			request.setAttribute("errore", "Username e/o Password errate");			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			System.out.println("Errore nel login");
			request.setAttribute("errore", "Errore nel login");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella conversione della foto");
			request.setAttribute("errore", "Errore nella conversione della foto");
		}
		
		// reinderizzo a index.jsp
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/index.jsp");
		disp.forward(request, response);		
	}

}
