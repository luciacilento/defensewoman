package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet");	
		String scelta = request.getParameter("scelta");

		String dest = null;

		if (scelta.equals("index")) {			
			dest = "index";
		} else if (scelta.equals("utente")) {
			dest = "utente";
		} else if (scelta.equals("registrazione")) {			
			dest = "registrazione";
		} else if (scelta.equals("modificaProfilo")) {
			dest = "modificaProfilo";
		} else if (scelta.equals("prodotti")) {			
			dest = "prodotti";
		} else if (scelta.equals("carrello")) {			
			dest = "carrello";
		} else if (scelta.equals("admin")) {			
			dest = "admin";
		} else if (scelta.equals("about")) {			
			dest = "about";
		} else {
			dest = "error"; //fare una jsp per la gestione degli errori
		}
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost");
	}

}
