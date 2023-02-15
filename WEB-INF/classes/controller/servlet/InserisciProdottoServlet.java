package controller.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.service.ProdottoService;
import model.bean.Prodotto;
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;

@MultipartConfig
@WebServlet("/inserisciProdotto")
public class InserisciProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di inserisciProdotto");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di inserisciProdotto");

		ProdottoService service = new ProdottoService();

		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		String pre = request.getParameter("prezzo");
		double prezzo = Double.parseDouble(pre);
		String cat = request.getParameter("FKCategoria");
		int FKCategoria = Integer.parseInt(cat);

		Prodotto prodotto = new Prodotto();
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);;
		prodotto.setPrezzo(prezzo);		
		prodotto.setFKCategoria(FKCategoria);

		// recupero il contesto
		ServletContext contesto = this.getServletContext();
		String dest = null;
		// RECUPERO foto
		InputStream uploadFoto = null; 
		Part fotoPart = request.getPart("imgProdotto");
		if (fotoPart!=null) {
			if (fotoPart.getSize()>0 && fotoPart.getName()!=null) {
				System.out.println("Il file è arrivato");				
				uploadFoto = fotoPart.getInputStream();							
			} 
		}
		
		try {
			// VALIDA DATI: chiamo validaProdotto
			service.validaProdotto(prezzo);
			
			// INSERIMENTO: chiamo inserisciProdotto
			service.inserisciProdotto(prodotto, uploadFoto);
			request.setAttribute("successo", "Prodotto inserito con successo!");
			dest = "inserisciProdotto";
			
		} catch (InvalidInputException e) {			
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("prodotto", prodotto);	
			request.setAttribute("errore", "Campi invalidi!");
			// reinderizzo a iserisciProdotto.jsp
			dest = "inserisciProdotto";
		} catch (InsertErrorException e) {			
			e.printStackTrace();
			System.err.println("Inserimento KO");		
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("prodotto", prodotto);	
			request.setAttribute("errore", "Errore nell'inserimento");
			// reinderizzo a iserisciProdotto.jsp
			dest = "inserisciProdotto";
		}
		
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);



	}



}
