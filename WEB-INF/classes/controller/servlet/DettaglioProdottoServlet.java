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

import controller.service.ProdottoService;
import model.bean.Prodotto;
import model.exception.NotFoundException;

@MultipartConfig
@WebServlet("/dettaglioProdotto")
public class DettaglioProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di DettaglioProdottoServlet");

		String idProdotto = request.getParameter("idProdotto");
		int id = Integer.parseInt(idProdotto);

		ProdottoService service = new ProdottoService();
		Prodotto prodotto = null;
		try {
			prodotto = service.cercaProdotto(id);
			String img = service.convertFileToString(prodotto.getImgProdotto());
			request.setAttribute("prodotto", prodotto);
			request.setAttribute("imgProdotto", img);
		} catch (NotFoundException e) {			
			e.printStackTrace();		
			request.setAttribute("errore","Prodotto non trovato");			
		} catch (SQLException e) {
			System.out.println("Problemi di SQL nel service");
			request.setAttribute("errore","Errore nel caricamento");	
			e.printStackTrace();
		}		
		ServletContext contesto = this.getServletContext();
		RequestDispatcher disp = contesto.getRequestDispatcher("/prodottoSingolo.jsp");
		disp.forward(request, response);	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di DettaglioProdottoServlet");
	}

}
