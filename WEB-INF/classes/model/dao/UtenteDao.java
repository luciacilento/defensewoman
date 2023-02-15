package model.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Utente;
import model.exception.DeleteErrorException;
import model.exception.FileNotFoundException;
import model.exception.InsertErrorException;
import model.exception.LoginKOException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;
import model.exception.UploadFileException;

public class UtenteDao implements DaoInterface<Utente>, UtenteDaoInterface {

	@Override
	public void insert(Utente utente,InputStream foto) throws InsertErrorException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectInsert = "INSERT INTO utente(username,pw,ruolo,imgProfilo,nome,cognome,dataNascita,numeroCellulare,mail,indirizzo,numCiv,citta,cap,nazione) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pre = connessione.prepareStatement(selectInsert);

			pre.setString(1, utente.getUsername());
			pre.setString(2, utente.getPw());
			pre.setString(3, "user");			
			pre.setBlob(4, foto);
			pre.setString(5, utente.getNome());
			pre.setString(6, utente.getCognome());
			Date dataNascita = new Date(utente.getDataNascita().getTime());
			pre.setDate(7, dataNascita);
			pre.setString(8, utente.getNumeroCellulare());
			pre.setString(9, utente.getMail());
			pre.setString(10, utente.getIndirizzo());
			pre.setString(11, utente.getNumCiv());
			pre.setString(12, utente.getCitta());
			pre.setString(13, utente.getCap());
			pre.setString(14, utente.getNazione());

			int tupleCoinvole = pre.executeUpdate();
			if(tupleCoinvole!=1) {
				throw new InsertErrorException();	
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			throw new InsertErrorException();						
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
	}

	@Override
	public Utente select(String userName) throws NotFoundException {
		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM utente WHERE username LIKE ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setString(1, userName);

			ResultSet res = pre.executeQuery();
			if (res.next()) {
				int idUtente = res.getInt("idUtente"); 
				String username = res.getString("username");
//				String psw = res.getString("pw");
//				String ruolo=res.getString("ruolo");
				Blob imgProfilo=res.getBlob("imgProfilo");
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				Date dataNascita=res.getDate("dataNascita");
				String numeroCellulare=res.getString("numeroCellulare");
				String mail=res.getString("mail");
				String via = res.getString("indirizzo");
				String numCiv = res.getString("numCiv");
				String citta = res.getString("citta");
				String provincia = res.getString("provincia");
				String cap = res.getString ("cap");
				String nazione = res.getString("nazione");
				int FKOrdine = res.getInt("FKOrdine");
				int FKPagamento = res.getInt("FKPagamento");

				Utente a = new Utente();
				a.setIdUtente(idUtente);
				a.setUsername(username);				
				a.setNome(nome);				
				a.setCognome(cognome);
				a.setDataNascita(dataNascita);
				a.setMail(mail);
				a.setNumeroCellulare(numeroCellulare);
				a.setIndirizzo(via);
				a.setNumCiv(numCiv);
				a.setCitta(citta);	
				a.setProvincia(provincia);
				a.setCap(cap);
				a.setNazione(nazione);
				a.setImgProfilo(imgProfilo);
				a.setFKOrdine(FKOrdine);
				a.setFKPagamento(FKPagamento);
				return a;
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new NotFoundException();
	}

	@Override
	public void update(Utente utente,InputStream foto, String username1) throws UpdateErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String update = "UPDATE utente set username=?, pw=?, imgProfilo=?, nome=?, cognome=?, dataNascita=?, numeroCellulare=?, mail=?, indirizzo=?, numCiv=?, citta=?, provincia=?, cap=?, nazione=? where username=?";

			PreparedStatement pre = connessione.prepareStatement(update);
			pre.setString(1, utente.getUsername());
			pre.setString(2, utente.getPw());
			pre.setBlob(3,foto);
			pre.setString(4, utente.getNome());
			pre.setString(5, utente.getCognome());
			Date dataNascita = new Date(utente.getDataNascita().getTime());
			pre.setDate(6, dataNascita);
			pre.setString(7, utente.getNumeroCellulare());
			pre.setString(8, utente.getMail());
			pre.setString(9, utente.getIndirizzo());
			pre.setString(10, utente.getNumCiv());
			pre.setString(11, utente.getCitta());
			pre.setString(12, utente.getProvincia());
			pre.setString(13, utente.getCap());
			pre.setString(14, utente.getNazione());				
			pre.setString(15, username1);

			int tuple = pre.executeUpdate();

			if (tuple !=1) {
				throw new UpdateErrorException();
			}		

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}		
	}	

	@Override
	public void delete(int idUtente) throws DeleteErrorException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String delete = "DELETE FROM utente WHERE idUtente = ?";

			PreparedStatement query = connessione.prepareStatement(delete);
			query.setInt(1, idUtente);

			int tuple = query.executeUpdate();

			if (tuple !=1) {
				throw new DeleteErrorException();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}

	}

	@Override
	public Utente login(String utente, String password) throws LoginKOException  {
		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM utente where username=? and pw=?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setString(1, utente);
			pre.setString(2, password);

			ResultSet res = pre.executeQuery();
			if ((res.next())) {
				int idUtente = res.getInt("idUtente"); 
				String username = res.getString("username");
//				String psw = res.getString("pw");
				String ruolo=res.getString("ruolo");
				Blob imgProfilo=res.getBlob("imgProfilo");
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				Date dataNascita=res.getDate("dataNascita");
				String numeroCellulare=res.getString("numeroCellulare");
				String mail=res.getString("mail");
				String via = res.getString("indirizzo");
				String numCiv = res.getString("numCiv");
				String citta = res.getString("citta");
				String provincia = res.getString("provincia");
				String cap = res.getString ("cap");
				String nazione = res.getString("nazione");

				Utente a = new Utente();
				a.setIdUtente(idUtente);
				a.setUsername(username);
				a.setRuolo(ruolo);
				a.setNome(nome);				
				a.setCognome(cognome);
				a.setDataNascita(dataNascita);
				a.setMail(mail);
				a.setNumeroCellulare(numeroCellulare);
				a.setIndirizzo(via);
				a.setNumCiv(numCiv);
				a.setCitta(citta);	
				a.setProvincia(provincia);
				a.setCap(cap);
				a.setNazione(nazione);
				a.setImgProfilo(imgProfilo);
				return a;		
			}			
		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new LoginKOException();		
	}

	public Blob recuperaFoto(int id) throws FileNotFoundException {
		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String script ="SELECT imgProfilo FROM utente WHERE idUtente=?";

			PreparedStatement pre = connessione.prepareStatement(script);
			pre.setInt(1, id);

			ResultSet res = pre.executeQuery();	
			if(res.next()) {
				// recupero di un Blob
				Blob file = res.getBlob(1);				
				return file;
			} else {
				throw new FileNotFoundException();
			}			

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		return null;		
	}

	// Correggere caricaFoto
	public int caricaFoto(InputStream foto) throws UploadFileException {
		try {
			Connection connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String fileInsert = "INSERT INTO utente(imgProfilo) VALUES(?)";

			PreparedStatement pre = connessione.prepareStatement(fileInsert);

			pre.setBlob(1,foto);
			int risultato = pre.executeUpdate();
			if (risultato==1) {
				System.out.println("Immagine caricata con successo sul DB");
			} else {
				throw new UploadFileException();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Driver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Problemi SQL");
			e.printStackTrace();
		}


		return 0;		
	}

	public boolean checkUsername(String username) {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String checkString ="SELECT username FROM utente WHERE username=?";

			PreparedStatement pre = connessione.prepareStatement(checkString);
			pre.setString(1, username);

			ResultSet res = pre.executeQuery();	

			if (res.next()) {
				return true;
			}
			return false;

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		return false;
	}

	public void insertFKkeys (Utente utente) throws UpdateErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String update = "UPDATE utente set FKPagamento=?,FKOrdine=? where username=?";

			PreparedStatement pre = connessione.prepareStatement(update);			
			pre.setInt(1, utente.getFKPagamento());
			pre.setInt(2, utente.getFKOrdine());
			pre.setString(3,utente.getUsername());

			int tuple = pre.executeUpdate();

			if (tuple !=1) {
				throw new UpdateErrorException();
			}	

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}		
	}

}
