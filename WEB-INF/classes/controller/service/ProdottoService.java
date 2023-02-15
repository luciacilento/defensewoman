package controller.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.Part;

import controller.dto.ProdottoDto;
import model.bean.Prodotto;
import model.dao.ProdottoDao;
import model.exception.InsertErrorException;
import model.exception.InvalidInputException;
import model.exception.NotFoundException;

public class ProdottoService {


	public void inserisciProdotto(Prodotto mioProdotto, InputStream foto) throws InsertErrorException  {
		ProdottoDao dao = new ProdottoDao();		
		dao.insert(mioProdotto,foto);
	}

	public Prodotto cercaProdotto(int idProdotto) throws NotFoundException {
		ProdottoDao dao = new ProdottoDao();		
		Prodotto prodotto = dao.select(idProdotto);
		return prodotto;
	}

	public ArrayList<ProdottoDto> listaProdottoDto (ArrayList<Prodotto> lista) throws SQLException {		
		ArrayList<ProdottoDto> listaDto = new ArrayList<ProdottoDto>();

		for (Prodotto p : lista) {
			ProdottoDto pDto=new ProdottoDto();
			pDto.setIdProdotto(p.getIdProdotto());
			pDto.setNome(p.getNome());				
			pDto.setPrezzo(p.getPrezzo());

			Blob imgP = p.getImgProdotto();
			String img = null;
			if (imgP!=null) {
				img = convertFileToString(imgP);
				pDto.setImgProdotto(img);
			} else {
				img = "assets/img/utenteDefault.png";
				pDto.setImgProdotto(img);				
			}
			listaDto.add(pDto);				
		}			

		return listaDto;
	}

	public void validaProdotto (double prezzo) throws InvalidInputException {
		if(prezzo<0) {
			System.out.println("Input NULL");
			throw new InvalidInputException();
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

	public ProdottoDto covertToDto (int id) throws NotFoundException, SQLException {				
		Prodotto p = cercaProdotto(id);
		ProdottoDto pDto = new ProdottoDto();
		pDto.setIdProdotto(p.getIdProdotto());
		pDto.setNome(p.getNome());				
		pDto.setPrezzo(p.getPrezzo());

		Blob imgP = p.getImgProdotto();
		String img = null;
		if (imgP!=null) {						
			img = convertFileToString(imgP);
			pDto.setImgProdotto(img);			
		} else {
			img = "assets/img/utenteDefault.png";
			pDto.setImgProdotto(img);				
		}
		return pDto;		
	}


	public ArrayList<ProdottoDto> listaProdotti () throws SQLException {
		ProdottoDao dao = new ProdottoDao();
		ArrayList<Prodotto> lista= dao.vediProdotti();
		ArrayList<ProdottoDto> listaDto = listaProdottoDto(lista);
		return listaDto;
	}	

	public ArrayList<ProdottoDto> listaProdottiCat (int idCategoria) throws NotFoundException, SQLException {
		ProdottoDao dao = new ProdottoDao();			
		ArrayList<Prodotto> lista = dao.filtroCategoria(idCategoria);
		ArrayList<ProdottoDto> listaDto = listaProdottoDto(lista);
		return listaDto;		
	}

	public ArrayList<ProdottoDto> listaProdottiPre (int prezzo1, int prezzo2) throws NotFoundException, SQLException {
		ProdottoDao dao = new ProdottoDao();	
		ArrayList<Prodotto> lista = dao.filtroPrezzi(prezzo1, prezzo2);
		ArrayList<ProdottoDto> listaDto = listaProdottoDto(lista);
		return listaDto;
	}

	public ArrayList<ProdottoDto> listaProdottiCatPre (int idCategoria, int prezzo1, int prezzo2) throws  SQLException, NotFoundException {
		ProdottoDao dao = new ProdottoDao();	
		ArrayList<Prodotto> lista = dao.filtroPrezzoCategoria(idCategoria, prezzo1, prezzo2);								 
		ArrayList<ProdottoDto> listaDto = listaProdottoDto(lista);
		return listaDto;
	}
	
	public ArrayList<ProdottoDto> listaProdottiPreMin (int prezzo1) throws NotFoundException, SQLException {
		ProdottoDao dao = new ProdottoDao();			
		ArrayList<Prodotto> lista = dao.filtroPrezzoMin(prezzo1);
		ArrayList<ProdottoDto> listaDto = listaProdottoDto(lista);
		return listaDto;		
	}

	public ArrayList<ProdottoDto> listaProdottiFiltrata (String idCategoria, String prezzo1, String prezzo2) throws NotFoundException, SQLException {
		ArrayList<ProdottoDto> lista = new ArrayList<ProdottoDto>();

		if (idCategoria != "" && prezzo1!="" && prezzo2!="") { //!idCategoria.isEmpty()
			int idCat = Integer.parseInt(idCategoria);
			int pre1 = Integer.parseInt(prezzo1);			
			int pre2 = Integer.parseInt(prezzo2);
			lista = listaProdottiCatPre(idCat, pre1, pre2);
		} else if(idCategoria != "" && prezzo2!="") {
			int idCat = Integer.parseInt(idCategoria);
			int pre2 = Integer.parseInt(prezzo2);
			lista = listaProdottiCatPre(idCat, 0, pre2);
		} else if(prezzo1!="" && prezzo2!="") {
			int pre1 = Integer.parseInt(prezzo1);			
			int pre2 = Integer.parseInt(prezzo2);
			lista = listaProdottiPre(pre1, pre2);
		} else if(prezzo2!="") {
			int pre2 = Integer.parseInt(prezzo2);
			lista = listaProdottiPre(0, pre2);
		} else if(prezzo1!="") {
			int pre1 = Integer.parseInt(prezzo1);
			lista = listaProdottiPreMin(pre1);
		} else if (idCategoria != "") {
			int idCat = Integer.parseInt(idCategoria);
			lista = listaProdottiCat(idCat);		
		} else {
			lista = listaProdotti();
		}

		return lista;
	}

}



