package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Ordine;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class OrdineDao implements OrdineDaoInterface {


	@Override
	public void insert(Ordine ordine) throws InsertErrorException {
		Connection miaConnessione=null;

		try {
			miaConnessione=DBConnectionSingleton.getInstance().recuperaConnessione();
			String selectInsert = "INSERT INTO ordine(indirizzoSpedizione,numeroCivico,citta,provincia,nazione,note) VALUES (?,?,?,?,?,?)";

			PreparedStatement pre = miaConnessione.prepareStatement(selectInsert,Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, ordine.getIndirizzoSpedizione());
			pre.setString(2, ordine.getNumeroCivico());
			pre.setString(3, ordine.getCitta());
			pre.setString(4, ordine.getProvincia());
			pre.setString(5, ordine.getNazione());
			pre.setString(6, ordine.getNote());		

			int tupleCoinvolte = pre.executeUpdate();
			if(tupleCoinvolte!=1) {
				throw new InsertErrorException();	
			}

			// ResultSet che restituisce l'idOrdine			
			ResultSet generatedKeys = pre.getGeneratedKeys();
			if (generatedKeys.next()) {
				ordine.setIdOrdine(generatedKeys.getInt(1));
				System.out.println(ordine.getIdOrdine());
			}
			else {
				throw new SQLException("ID non trovato dopo insert");
			}
			
		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita");
			System.out.println("Driver non agganciato");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita");
			System.err.println("Parametri SQL non corretti");
			e.printStackTrace();

		}finally {
			try {
				miaConnessione.close();
				System.out.println("connessione chiusa");
			} catch (SQLException e) {
				System.out.println("Connessione aperta");
				e.printStackTrace();
			}
		}
	}

	public void update(Ordine ordineDaModificare) throws UpdateErrorException {

		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();


			String selectUpdate = "UPDATE ordine SET indirizzoSpedizione=?, numeroCivico=?, citta=?, provincia=?, nazione=?, note=? WHERE idOrdine=?";

			PreparedStatement pre = connessione.prepareStatement(selectUpdate);

			pre.setString(1, ordineDaModificare.getIndirizzoSpedizione());
			pre.setString(2,  ordineDaModificare.getNumeroCivico());
			pre.setString(3, ordineDaModificare.getCitta());
			pre.setString(4, ordineDaModificare.getProvincia());
			pre.setString(5, ordineDaModificare.getNazione());
			pre.setString(6,ordineDaModificare.getNote());
			pre.setInt(7, ordineDaModificare.getIdOrdine());

			int tupleCoinvole = pre.executeUpdate();
			if (tupleCoinvole != 1) {
				throw new UpdateErrorException();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita");
			System.err.println("Driver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita");
			System.err.println("Parametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
	}

	public Ordine select (int id) throws NotFoundException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM ordine WHERE idOrdine LIKE ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, id);

			ResultSet res = pre.executeQuery();

			if (res.next()) {
				int idOrdine = res.getInt("idOrdine");	
				String indirizzoSpedizione = res.getString("indirizzoSpedizione");
				String numeroCivico = res.getString("numeroCivico");
				String citta = res.getString("citta");
				String provincia = res.getString("provincia");
				String nazione = res.getString("nazione");
				String note = res.getString("note");

				Ordine ordine = new Ordine();
				ordine.setIdOrdine(idOrdine);
				ordine.setIndirizzoSpedizione(indirizzoSpedizione);
				ordine.setNumeroCivico(numeroCivico);
				ordine.setCitta(citta);
				ordine.setProvincia(provincia);	
				ordine.setNazione(nazione);
				ordine.setNote(note);
				return ordine;
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

	public void delete(int idOrdine) throws DeleteErrorException {

		Connection miaConnessione=null;

		try {
			miaConnessione=DBConnectionSingleton.getInstance().recuperaConnessione();

			String deleteOrdine="DELETE FROM ordine WHERE idOrdine = ?";

			PreparedStatement pre=miaConnessione.prepareStatement(deleteOrdine);

			pre.setInt(1, idOrdine);

			int tuple = pre.executeUpdate();

			if (tuple!=1) {
				throw new DeleteErrorException();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Driver non agganciato");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Problemi SQL");
			e.printStackTrace();

		}finally {		
			try {
				miaConnessione.close();
				System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.out.println("Connessione aperta");
				e.printStackTrace();
			}
		}


	}




}
