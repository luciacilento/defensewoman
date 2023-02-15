package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Pagamento;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class PagamentoDao implements PagamentoDaoInterface{

	public void insert(Pagamento metodoP) throws InsertErrorException {
		
		Connection connessione = null;
		
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectInsert = "INSERT INTO pagamento(idPagamento, nomeIntestatario, cognomeIntestatario, circuitoCarta, cartaDiCredito, scadenzaCarta, indirizzoFatturazione, codiceSicurezza) values (?,?,?,?,?,?,?,?)";

			PreparedStatement pre = connessione.prepareStatement(selectInsert,Statement.RETURN_GENERATED_KEYS);

			pre.setInt(1, metodoP.getIdPagamento());
			pre.setString(2, metodoP.getNomeIntestatario());
			pre.setString(3, metodoP.getCognomeIntestatario());
			pre.setString(4, metodoP.getCircuitoCarta());
			pre.setString(5, metodoP.getCartaDiCredito());
			Date scadenzaCarta = new Date(metodoP.getScadenzaCarta().getTime());
			pre.setDate(6, scadenzaCarta);
			pre.setString(7, metodoP.getIndirizzoFatturazione());
			pre.setString(8, metodoP.getCodiceSicurezza());


			int tupleCoinvolte = pre.executeUpdate();
			if (tupleCoinvolte != 1) {
				throw new InsertErrorException();
			}

			ResultSet generatedKeys = pre.getGeneratedKeys();
			if (generatedKeys.next()) {
				metodoP.setIdPagamento(generatedKeys.getInt(1));
			}
			else {
				throw new SQLException("ID non trovato dopo insert");
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

	public Pagamento select(int idPayment) throws NotFoundException {
		
		Connection connessione = null;
		
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM pagamento WHERE idPagamento LIKE ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, idPayment);

			ResultSet res = pre.executeQuery();
			if (res.next()) {

				int idPagamento = res.getInt("idPagamento");
				String nomeIntestatario = res.getString("nomeIntestatario");
				String cognomeIntestatario = res.getString("cognomeIntestatario");
				String circuitoCarta=res.getString("circuitoCarta");
				String cartaDiCredito = res.getString("cartaDiCredito");
				Date scadenzaCarta = res.getDate("scadenzaCarta");
				String indirizzoFatturazione = res.getString("indirizzoFatturazione");
				String codiceSicurezza = res.getString("codiceSicurezza");

				Pagamento p = new Pagamento();
				p.setIdPagamento(idPagamento);
				p.setNomeIntestatario(nomeIntestatario);
				p.setCognomeIntestatario(cognomeIntestatario);
				p.setCircuitoCarta(circuitoCarta);
				p.setCartaDiCredito(cartaDiCredito);
				p.setScadenzaCarta(scadenzaCarta);
				p.setIndirizzoFatturazione(indirizzoFatturazione);
				p.setCodiceSicurezza(codiceSicurezza);

				return p;
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

	public Pagamento recuperaDatiPagamentoUtente(int idPayment) throws NotFoundException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT idPagamento, nomeIntestatario, cognomeIntestatario, circuitoCarta, cartaDiCredito, scadenzaCarta, indirizzoFatturazione FROM pagamento WHERE idPagamento = ?"; // no codice sicurezza, no id, ed fk;

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, idPayment);

			ResultSet res = pre.executeQuery();

			if (res.next()) {

				int idPagamento= res.getInt(1);
				String nomeIntestatario = res.getString(2);
				String cognomeIntestatario = res.getString(3);
				String circuitoCarta= res.getString(4);
				String cartaDiCredito = res.getString(5);
				Date scadenzaCarta = res.getDate(6);
				String indirizzoFatturazione = res.getString(7);
				

				Pagamento p = new Pagamento();
				p.setIdPagamento(idPagamento);
				p.setNomeIntestatario(nomeIntestatario);
				p.setCognomeIntestatario (cognomeIntestatario);
				p.setCircuitoCarta(circuitoCarta);
				p.setCartaDiCredito(cartaDiCredito);
				p.setScadenzaCarta(scadenzaCarta);
				p.setIndirizzoFatturazione(indirizzoFatturazione);

				return p;
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

	public void update(Pagamento pagamentoDaModificare) throws UpdateErrorException {

		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();
			String selectUpdate = "UPDATE pagamento SET nomeIntestatario=?, cognomeIntestatario=?, circuitoCarta=?,cartaDiCredito=?,scadenzaCarta=?,indirizzoFatturazione=?, codiceSicurezza=? WHERE idPagamento=?";

			PreparedStatement pre = connessione.prepareStatement(selectUpdate);

			pre.setString(1, pagamentoDaModificare.getNomeIntestatario());
			pre.setString(2, pagamentoDaModificare.getCognomeIntestatario());
			pre.setString(3, pagamentoDaModificare.getCircuitoCarta());
			pre.setString(4, pagamentoDaModificare.getCartaDiCredito());
			Date scadenzaCarta = new Date(pagamentoDaModificare.getScadenzaCarta().getTime());
			pre.setDate(5, scadenzaCarta);
			pre.setString(6, pagamentoDaModificare.getIndirizzoFatturazione());
			pre.setString(7, pagamentoDaModificare.getCodiceSicurezza());
			pre.setInt(8, pagamentoDaModificare.getIdPagamento());

			int tupleCoinvolte = pre.executeUpdate();
			if (tupleCoinvolte != 1) {
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
				//System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
	}

	public void delete(int idPagamento) throws DeleteErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String delete = "DELETE FROM pagamento WHERE idPagamento = ?";

			PreparedStatement pre = connessione.prepareStatement(delete);
			pre.setInt(1, idPagamento);

			int tuple = pre.executeUpdate();

			if (tuple != 1) {
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
				System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.out.println("Connessione ancora aperta ... ");
				e.printStackTrace();
			}
		}
	}

}
