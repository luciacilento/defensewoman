package controller.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.http.Part;

import controller.dto.UtenteDto;
import model.bean.Utente;
import model.dao.UtenteDao;
import model.exception.FileNotFoundException;
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;
import model.exception.InvalidUsernameException;
import model.exception.LoginKOException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;
import model.exception.UploadFileException;

public class UtenteService {

	public void registraUtente(Utente nuovoUtente, InputStream foto) throws InsertErrorException {
		UtenteDao dao = new UtenteDao();		
		dao.insert(nuovoUtente,foto);		

	}

	public Utente cercaUtente(String user) throws NotFoundException {
		UtenteDao dao = new UtenteDao();
		Utente UtenteTrovato = dao.select(user);
		return UtenteTrovato;
	}

	public UtenteDto login (String user, String psw) throws LoginKOException, FileNotFoundException, SQLException {
		System.out.println("Sono nel login UtenteService");
		UtenteDao dao = new UtenteDao();		
		Utente utenteLog = dao.login(user,psw);

		Blob fotoUtente;
		String img = null;
		UtenteDto utente = new UtenteDto();

		utente.setNome(utenteLog.getNome());
		utente.setRuolo(utenteLog.getRuolo());
		utente.setUsername(utenteLog.getUsername());			
		fotoUtente = dao.recuperaFoto(utenteLog.getIdUtente());
		if (fotoUtente != null) { 				
			img = convertFileToString(fotoUtente);
			utente.setImgProfilo(img);
		} else {
			img = "assets/img/utenteDefault.png";
			utente.setImgProfilo(img);				
		}
		return utente;
	}

	public void caricaFoto(InputStream foto) throws UploadFileException { 
		UtenteDao dao = new UtenteDao();
		dao.caricaFoto(foto);				
	}


	public void modificaProfilo(Utente utente, InputStream foto, String username1) throws UpdateErrorException {
		UtenteDao dao = new UtenteDao();		
		dao.update(utente,foto, username1);		
	}	

	public void checkUsername (String username) throws InvalidInputException, InvalidUsernameException {
		UtenteDao dao = new UtenteDao();
		boolean check = dao.checkUsername(username);
		if (username==null||username.isEmpty()) {
			throw new InvalidInputException();
		} else if (check) {
			System.out.println("Username INVALIDO");
			throw new InvalidUsernameException();
		} else {
			System.out.println("Validazione OK");
		}
	}

	public void validaUtente(String password, String nome, String cognome, String email) throws InvalidInputException {	
		if (password==null||nome==null||cognome==null||email==null) {
			System.out.println("Input NULL");
			throw new InvalidInputException();
		} else if (password.isEmpty()||nome.isEmpty()||cognome.isEmpty()||email.isEmpty()) {
			System.out.println("Input VUOTO");
			throw new InvalidInputException();
		} else {
			System.out.println("Validazione OK");
		}			
	}

	public String convertFileToString (Blob file) throws SQLException {
		int lunghezzaFile;
		lunghezzaFile = (int) file.length();
		byte[] fileDB = file.getBytes(1, lunghezzaFile);
		String filex = "data:image/png;base64," +Base64.getEncoder().encodeToString(fileDB);
		return filex;
	}

	public InputStream convertFiletoInputStream (Part file) throws IOException {
		InputStream filex = null;
		if (file!=null) {
			if (file.getSize()>0 && file.getName()!=null) {
				System.out.println("Il file è arrivato");				
				filex = file.getInputStream();						
			} 
		}
		return filex;
	}

	public void insertFKkeys (Utente utente) throws UpdateErrorException {
		UtenteDao dao = new UtenteDao();
		dao.insertFKkeys(utente);
	}
	


}
