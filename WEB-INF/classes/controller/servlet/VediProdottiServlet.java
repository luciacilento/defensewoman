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

import controller.dto.ProdottoDto;
import controller.service.CategoriaService;
import controller.service.ProdottoService;
import model.bean.Categoria;
import model.exception.NotFoundException;

@MultipartConfig
@WebServlet("/vediProdotti")
public class VediProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di vediProdottiServlet");	

		ProdottoService servicePro = new ProdottoService();
		CategoriaService serviceCat = new CategoriaService();
		ArrayList<ProdottoDto> lista = new ArrayList<ProdottoDto>();
		ArrayList<Categoria> categorie = serviceCat.vediCategorie();

		HttpSession sessione = request.getSession();
		try {
			lista = servicePro.listaProdottiFiltrata("", "", "");
			sessione.setAttribute("prodotti", lista);
			sessione.setAttribute("categorie", categorie);
			
		} catch (SQLException | NotFoundException e) {			
			e.printStackTrace();
			request.setAttribute("errore","Errore nel caricamento");
		}	

		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/prodotti.jsp");
		disp.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di vediProdottiServlet");	

		ProdottoService service = new ProdottoService();		
		ArrayList<ProdottoDto> lista = new ArrayList<ProdottoDto>();

		// recupero i FILTRI
		String idCategoria = request.getParameter("idCategoria");
		String prezzo1 = request.getParameter("prezzo1"); 
		String prezzo2 = request.getParameter("prezzo2");

		HttpSession sessione = request.getSession();
		try {	
			lista = service.listaProdottiFiltrata(idCategoria, prezzo1, prezzo2);
			sessione.setAttribute("prodotti", lista);
			
		} catch (NotFoundException | SQLException e) {			
			e.printStackTrace();
			request.setAttribute("errore","Errore nel caricamento");
		}			
		
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/prodotti.jsp");
		disp.forward(request, response);
	}

}
