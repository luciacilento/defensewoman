package controller.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.Part;

import controller.dto.UtenteDto;
import controller.service.UtenteService;
import model.bean.Utente;
import model.exception.FileNotFoundException;
import model.exception.InvalidInputException;
import model.exception.InvalidUsernameException;
import model.exception.LoginKOException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

@MultipartConfig
@WebServlet("/modificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di ModificaProfiloServlet");
		UtenteService service = new UtenteService();

		// recupero l'UtenteDTO dalla sessione
		HttpSession sessione = request.getSession();			
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");
		// recupero il contesto
		ServletContext contesto = this.getServletContext();
		String dest = null;

		Utente utente = new Utente();
		try {
			utente = service.cercaUtente(utenteSession.getUsername());
			System.out.println(utente);
			// inoltro tutti i dettagli dell'Utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);

			// reinderizzo a modificaProfilo.jsp			
			dest = "modificaProfilo";

		} catch (NotFoundException e) {			
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella ricerca dell'utente");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} 
		
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di ModificaProfiloServlet");

		UtenteService service = new UtenteService();
		HttpSession sessione = request.getSession();
		UtenteDto utenteSession = (UtenteDto) sessione.getAttribute("utente");
		String usernameUtente = utenteSession.getUsername();

		Utente utenteBean = null;
		try {
			utenteBean = service.cercaUtente(usernameUtente);

		} catch (NotFoundException e1) {			
			e1.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento");
		}
		Blob img = utenteBean.getImgProfilo();

		// recupero i parametri
		String username = request.getParameter("user");
		String password = request.getParameter("psw");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");			
		String numCellulare = request.getParameter("cell");
		String email = request.getParameter("mail");
		String indirizzo = request.getParameter("indirizzo");		
		String numCiv = request.getParameter("numCiv");
		String citta = request.getParameter("citta");
		String prov = request.getParameter("provincia");
		String cap = request.getParameter("cap");
		String nazione = request.getParameter("nazione");

		Utente utente = new Utente();		
		utente.setUsername(username);
		utente.setPw(password);
		utente.setNome(nome);
		utente.setCognome(cognome);		
		utente.setNumeroCellulare(numCellulare);
		utente.setMail(email);
		utente.setIndirizzo(indirizzo);
		utente.setNumCiv(numCiv);
		utente.setCitta(citta);
		utente.setProvincia(prov);
		utente.setCap(cap);
		utente.setNazione(nazione);

		Date dataNascita = new Date();
		String dataNascitaInput = request.getParameter("dataNascita"); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		// recupero il contesto
		ServletContext contesto = this.getServletContext();
		String dest = null;

		try {
			// SET dataNascita			
			dataNascita = sdf.parse(dataNascitaInput);
			utente.setDataNascita(dataNascita);		

			// RECUPERO foto
			InputStream uploadFoto = null;			
			Part fotoPart = request.getPart("imgProfilo");
			InputStream foto1 = service.convertFiletoInputStream(fotoPart);
			if (foto1!=null) {
				uploadFoto = foto1;
			} else {
				if (img!=null) {
					uploadFoto = img.getBinaryStream();	
				}									
			}

			// CHECK USERNAME: chiamo checkUsername se username !=
			if(!usernameUtente.equals(username)) {
				service.checkUsername(username);
			}			
			// VALIDA DATI: chiamo validaUtente
			service.validaUtente(password, nome, cognome, email);

			// UPDATE: chiamo modificaProfilo
			service.modificaProfilo(utente, uploadFoto, usernameUtente);

			// LOGIN: avvio sessione con login dell'utenteRegistrato
			UtenteDto utenteModificato = service.login(username, password);				
			sessione.setAttribute("utente", utenteModificato);
			request.setAttribute("successo", "Modifica avvenuta con successo");

			// reinderizzo a index.jsp
			dest = "index";

		} catch (ParseException e) {			
			e.printStackTrace();
			System.err.println("Modifica KO");			
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Errore nell'inserimento della data");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} catch (LoginKOException e) {
			e.printStackTrace();
			System.err.println("Login KO");
			request.setAttribute("errore", "Username e/o Password errare");
			// reinderizzo a index.jsp
			dest = "index";
		} catch (UpdateErrorException e) {			
			e.printStackTrace();
			System.err.println("Modifica KO");		
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Errore nella modifica");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} catch (InvalidInputException e) {					
			utente.setDataNascita(dataNascita);
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Compilare tutti i campi!");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} catch (InvalidUsernameException e) {
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Username già presente nel sistema");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} catch (SQLException e) {			
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Errore nella conversione della foto");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utente", utenteSession);
			request.setAttribute("utenteBean", utente);	
			request.setAttribute("errore", "Errore nella modifica");
			// reinderizzo a modificaProfilo.jsp
			dest = "modificaProfilo";
		} 
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);

	}

}
