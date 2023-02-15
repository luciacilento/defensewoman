package model.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Prodotto;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class ProdottoDao implements FiltriProdottoInterface {

	@Override
	public void insert(Prodotto prodottoInsert, InputStream foto) throws InsertErrorException {
		Connection connessione = null;
		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectInsert = "INSERT INTO prodotto(nome,descrizione,prezzo,imgProdotto,FKCategoria) VALUES (?,?,?,?,?)";

			PreparedStatement pre = connessione.prepareStatement(selectInsert);

			pre.setString(1, prodottoInsert.getNome());
			pre.setString(2, prodottoInsert.getDescrizione());
			pre.setDouble(3, prodottoInsert.getPrezzo());
			pre.setBlob(4, prodottoInsert.getImgProdotto());
			pre.setInt(5, prodottoInsert.getFKCategoria());

			int tupleCoinvolte = pre.executeUpdate();

			if(tupleCoinvolte!=1) {
				throw new InsertErrorException();	
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

	public Prodotto select (int id) throws NotFoundException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM prodotto WHERE idProdotto LIKE ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, id);

			ResultSet res = pre.executeQuery();

			if (res.next()) {
				int idProdotto = res.getInt("idProdotto");	
				int disponibilita = res.getInt("disponibilita");
				String nome = res.getString("nome");
				String descrizione = res.getString("descrizione");
				Double prezzo = res.getDouble("prezzo");
				Blob imgProdotto = res.getBlob("imgProdotto");
				int fkCategoria = res.getInt("FKCategoria");				

				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setDisponibilita(disponibilita);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);	
				prodotto.setImgProdotto(imgProdotto);
				prodotto.setFKCategoria(fkCategoria);			
				return prodotto;
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

	public void update(Prodotto prodottoDaModificare,InputStream foto) throws UpdateErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectUpdate = "UPDATE prodotto SET disponibilita=?,nome=?,descrizione=?,prezzo=?,imgProdotto=?,FKCategoria=? WHERE idProdotto=?";

			PreparedStatement pre = connessione.prepareStatement(selectUpdate);

			pre.setInt(1, 1);
			pre.setString(2, prodottoDaModificare.getNome());
			pre.setString(3, prodottoDaModificare.getDescrizione());
			pre.setDouble(4, prodottoDaModificare.getPrezzo());
			pre.setBlob(5, foto);
			pre.setInt(6, prodottoDaModificare.getFKCategoria());			
			pre.setInt(8, prodottoDaModificare.getIdProdotto());

			int tupleCoinvolte = pre.executeUpdate();

			if(tupleCoinvolte!=1) {
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

	@Override
	public void delete(int idProdotto) throws DeleteErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String delete = "DELETE FROM prodotto WHERE idProdotto = ?"; 

			PreparedStatement pre = connessione.prepareStatement(delete);
			pre.setInt(1,idProdotto);

			int tuple = pre.executeUpdate();

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
				System.out.println("Connessione ancora aperta ... ");
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<Prodotto> vediProdotti() {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectAll = "SELECT * FROM prodotto";

			PreparedStatement pre = connessione.prepareStatement(selectAll);
			ResultSet res = pre.executeQuery();	

			ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();		

			while (res.next()) {

				int idProdotto = res.getInt("idProdotto");
				int disponibilita = res.getInt("disponibilita");
				String nome = res.getString("nome");
				String descrizione = res.getString("descrizione");
				double prezzo = res.getDouble("prezzo");
				Blob imgProdotto = res.getBlob("imgProdotto");
				int fkCategoria = res.getInt("FKCategoria");				

				Prodotto product = new Prodotto();
				product.setIdProdotto(idProdotto);
				product.setDisponibilita(disponibilita);
				product.setNome(nome);
				product.setDescrizione(descrizione);
				product.setPrezzo(prezzo);
				product.setImgProdotto(imgProdotto);
				product.setFKCategoria(fkCategoria);				

				listaProdotti.add(product);
			}
			return listaProdotti;

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

	/**
	 * Select prodotti in un RANGE di PREZZO
	 * @param1 int prezzo1
	 * @param2 int prezzo2
	 * @return ArrayList<Prodotto>
	 * @throws NotFoundException 
	 * 
	 */
	@Override
	public ArrayList<Prodotto> filtroPrezzi(int prezzo1, int prezzo2) throws NotFoundException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT idProdotto, disponibilita, nome, descrizione, prezzo, imgProdotto FROM prodotto WHERE prezzo BETWEEN ? AND ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, prezzo1);
			pre.setInt(2, prezzo2);

			ResultSet res = pre.executeQuery();

			ArrayList<Prodotto> listaRisultato = new ArrayList<Prodotto>();

			while (res.next()) {
				int idProdotto = res.getInt(1);
				int disponibilita = res.getInt(2);
				String nome = res.getString(3);
				String descrizione = res.getString(4);
				Double prezzo = res.getDouble(5);
				Blob imgProdotto = res.getBlob(6);

				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setDisponibilita(disponibilita);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setImgProdotto(imgProdotto);

				listaRisultato.add(prodotto);

			}			
			return listaRisultato;		

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

	/**
	 * Select che restiuisce TUTTI i prodotti in base alla CATEGORIA
	 * @param int idCategoria
	 * @return ArrayList <Prodotto>
	 * @trhows NotFoundException
	 * 
	 */
	@Override
	public ArrayList<Prodotto> filtroCategoria(int idCategoria) throws NotFoundException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT idProdotto, disponibilita, nome, descrizione, prezzo, imgProdotto FROM categoria INNER JOIN prodotto ON categoria.idCategoria=prodotto.FKCategoria WHERE idCategoria=?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, idCategoria);

			ResultSet res = pre.executeQuery();

			ArrayList<Prodotto> listaRisultato = new ArrayList<Prodotto>();

			while(res.next()) {
				int idProdotto = res.getInt(1);
				int disponibilita = res.getInt(2);
				String nome = res.getString(3);
				String descrizione = res.getString(4);
				Double prezzo = res.getDouble(5);
				Blob imgProdotto = res.getBlob(6);

				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setDisponibilita(disponibilita);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setImgProdotto(imgProdotto);

				listaRisultato.add(prodotto);
			}

			return listaRisultato;

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				// System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new NotFoundException();

	}
	
	public ArrayList<Prodotto> filtroPrezzoMin(int prezzo1) throws NotFoundException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT idProdotto, disponibilita, nome, descrizione, prezzo, imgProdotto FROM prodotto WHERE prezzo >= ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, prezzo1);

			ResultSet res = pre.executeQuery();

			ArrayList<Prodotto> listaRisultato = new ArrayList<Prodotto>();

			while(res.next()) {
				int idProdotto = res.getInt(1);
				int disponibilita = res.getInt(2);
				String nome = res.getString(3);
				String descrizione = res.getString(4);
				Double prezzo = res.getDouble(5);
				Blob imgProdotto = res.getBlob(6);

				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setDisponibilita(disponibilita);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setImgProdotto(imgProdotto);

				listaRisultato.add(prodotto);
			}

			return listaRisultato;

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				// System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new NotFoundException();

	}
	
	public ArrayList<Prodotto> filtroPrezzoCategoria(int idCategoria, int prezzo1, int prezzo2) throws NotFoundException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT idProdotto, disponibilita, nome, descrizione, prezzo, imgProdotto FROM categoria c INNER JOIN prodotto p ON c.idCategoria=p.FKCategoria WHERE idCategoria=? AND prezzo BETWEEN ? AND ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, idCategoria);
			pre.setInt(2, prezzo1);
			pre.setInt(3, prezzo2);

			ResultSet res = pre.executeQuery();

			ArrayList<Prodotto> listaRisultato = new ArrayList<Prodotto>();

			while(res.next()) {
				int idProdotto = res.getInt(1);
				int disponibilita = res.getInt(2);
				String nome = res.getString(3);
				String descrizione = res.getString(4);
				Double prezzo = res.getDouble(5);
				Blob imgProdotto = res.getBlob(6);

				Prodotto prodotto = new Prodotto();
				prodotto.setIdProdotto(idProdotto);
				prodotto.setDisponibilita(disponibilita);
				prodotto.setNome(nome);
				prodotto.setDescrizione(descrizione);
				prodotto.setPrezzo(prezzo);
				prodotto.setImgProdotto(imgProdotto);

				listaRisultato.add(prodotto);
			}

			return listaRisultato;

		} catch (ClassNotFoundException e) {
			System.err.println("Connessione non riuscita\nDriver non riconosciuti");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Connessione non riuscita\nParametri SQL non corretti");
			e.printStackTrace();
		} finally {
			try {
				connessione.close();
				// System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new NotFoundException();

	}


}
