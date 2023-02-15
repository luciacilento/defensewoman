package model.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Categoria;
import model.exception.DeleteErrorException;
import model.exception.InsertErrorException;
import model.exception.NotFoundException;
import model.exception.UpdateErrorException;

public class CategoriaDao implements CategoriaDaoInterface {

	@Override	
	public void insert(Categoria nuovaCategoria, InputStream foto) throws InsertErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectInsert = "INSERT INTO categoria(nomeCategoria, descrizioneCategoria, imgCategoria) VALUES (?,?,?)";

			PreparedStatement pre = connessione.prepareStatement(selectInsert);

			pre.setString(1, nuovaCategoria.getNomeCategoria());
			pre.setString(2, nuovaCategoria.getDescrizioneCategoria());
			pre.setBlob(3, nuovaCategoria.getImgCategoria());

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


	@Override
	public Categoria select(int idCategoria) throws NotFoundException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectSearch = "SELECT * FROM categoria WHERE idCategoria LIKE ?";

			PreparedStatement pre = connessione.prepareStatement(selectSearch);

			pre.setInt(1, idCategoria);

			ResultSet res = pre.executeQuery();
			if (res.next()) {
				int idCategory = res.getInt("idCategoria");	
				String nomeCategoria = res.getString("nomeCategoria");
				String descrizioneCategoria = res.getString("descrizioneCategoria");
				Blob imgCategoria = res.getBlob("imgCategoria");

				Categoria categoriaRecuperata = new Categoria();
				categoriaRecuperata.setIdCategoria(idCategory);
				categoriaRecuperata.setNomeCategoria(nomeCategoria);
				categoriaRecuperata.setDescrizioneCategoria(descrizioneCategoria);	
				categoriaRecuperata.setImgCategoria(imgCategoria);
				return categoriaRecuperata;
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
				System.err.println("Connessione ancora aperta");
				e.printStackTrace();
			}
		}
		throw new NotFoundException();
	}


	@Override
	public void update(Categoria aggiornaCategoria, InputStream foto) throws UpdateErrorException {
		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();
			String selectUpdate = "UPDATE categoria SET nomeCategoria=?,descrizioneCategoria=?,imgCategoria=? WHERE idCategoria=?";

			PreparedStatement pre = connessione.prepareStatement(selectUpdate);

			pre.setString(1, aggiornaCategoria.getNomeCategoria());
			pre.setString(2, aggiornaCategoria.getDescrizioneCategoria());
			pre.setBlob(3, aggiornaCategoria.getImgCategoria());
			pre.setInt(4,  aggiornaCategoria.getIdCategoria());

			int tupleCoinvolte = pre.executeUpdate();
			if(tupleCoinvolte !=1) {
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


	@Override
	public void delete(int idCategoria) throws DeleteErrorException {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String delete = "DELETE FROM categoria WHERE idCategoria = ?"; 

			PreparedStatement pre = connessione.prepareStatement(delete);
			pre.setInt(1,idCategoria);

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
				System.out.println("Connessione chiusa");
			} catch (SQLException e) {
				System.out.println("Connessione ancora aperta ... ");
				e.printStackTrace();
			}
		}

	}
	
	
	@Override	
	public ArrayList<Categoria> vediCategorie() {

		Connection connessione = null;

		try {
			connessione = DBConnectionSingleton.getInstance().recuperaConnessione();

			String selectAll = "SELECT * FROM categoria";

			PreparedStatement pre = connessione.prepareStatement(selectAll);
			ResultSet res = pre.executeQuery();	

			ArrayList<Categoria> listaCategorie = new ArrayList<Categoria>();		

			while (res.next()) {

				int idCategoria = res.getInt("idCategoria");
				String nome = res.getString("nomeCategoria");
				String descrizione = res.getString("descrizioneCategoria");
				//Blob imgCategoria = res.getBlob("imgCategoria");				

				Categoria cat  = new Categoria();
				cat.setIdCategoria(idCategoria);
				cat.setNomeCategoria(nome);
				cat.setDescrizioneCategoria(descrizione);


				listaCategorie.add(cat);
			}
			return listaCategorie;

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
	
	
	
	

}
