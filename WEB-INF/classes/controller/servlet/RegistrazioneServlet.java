package controller.servlet;

import java.io.IOException;
import java.io.InputStream;
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
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;
import model.exception.InvalidUsernameException;
import model.exception.LoginKOException;

@WebServlet("/registrazione")
@MultipartConfig
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doGet di RegistrazioneServlet");	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel doPost di RegistrazioneServlet");	

		UtenteService service = new UtenteService();

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

		String dataNascitaInput = request.getParameter("dataNascita"); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		// recupero il contesto
		ServletContext contesto = this.getServletContext();
		String dest = null;
		try {
			// SET dataNascita
			Date dataNascita;
			dataNascita = sdf.parse(dataNascitaInput);
			utente.setDataNascita(dataNascita);						

			// RECUPERO foto
			InputStream uploadFoto = null; 
			Part fotoPart = request.getPart("imgProfilo");				
			uploadFoto = service.convertFiletoInputStream(fotoPart);	
			
			// CHECK USERNAME: chiamo checkUsername
			service.checkUsername(username);

			// VALIDA DATI: chiamo validaUtente
			service.validaUtente(password, nome, cognome, email);

			// REGISTRAZIONE: chiamo registraUtente
			service.registraUtente(utente, uploadFoto);	

			// LOGIN: avvio sessione con login dell'utenteRegistrato
			UtenteDto utenteRegistrato = service.login(username, password);			
			HttpSession sessione = request.getSession();
			request.setAttribute("successo", "Registrazione avvenuta con successo");
			sessione.setAttribute("utente", utenteRegistrato);

			// reinderizzo a index.jsp
			dest = "index";

		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Registrazione KO");			
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utenteReg", utente);	
			request.setAttribute("errore", "Errore nell'inserimento della data");
			// reinderizzo a registrazione.jsp
			dest = "registrazione";
		} catch (InsertErrorException e) {
			e.printStackTrace();			
			System.err.println("Registrazione KO");		
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utenteReg", utente);	
			request.setAttribute("errore", "Errore nella registrazione");
			// reinderizzo a registrazione.jsp
			dest = "registrazione";
		} catch (LoginKOException e) {			
			e.printStackTrace();
			System.err.println("Login KO");
			request.setAttribute("errore", "Username e/o Password errare");
			// reinderizzo a index.jsp
			dest = "index";
		} catch (InvalidInputException e) {			
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utenteReg", utente);	
			request.setAttribute("errore", "Compilare tutti i campi!");
			// reinderizzo a registrazione.jsp
			dest = "registrazione";
		} catch (InvalidUsernameException e) {			
			e.printStackTrace();
			// restituisco l'errore e i campi compilati dall'utente
			request.setAttribute("utenteReg", utente);	
			request.setAttribute("errore", "Username già presente nel sistema");
			// reinderizzo a registrazione.jsp
			dest = "registrazione";
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			System.out.println("Errore nel login");
			request.setAttribute("errore", "Errore nel login");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella conversione della foto");
			request.setAttribute("errore", "Errore nella conversione della foto");
		}
		RequestDispatcher disp = contesto.getRequestDispatcher("/"+dest+".jsp");
		disp.forward(request, response);

	}

}
