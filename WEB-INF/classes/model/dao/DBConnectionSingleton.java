package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionSingleton {

	private final static String DB_DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
	private final static String USER= "root";
	private final static String PASSWORD= "root";
	private final static String NOME_SCHEMA= "defensewoman";
	private final static String PROPERTY ="?useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Amsterdam";
	private final static String URL="jdbc:mysql://localhost:3306/" +NOME_SCHEMA+PROPERTY; 

	private static DBConnectionSingleton oggettoSingleton;

	private DBConnectionSingleton() {
	}

	public static DBConnectionSingleton getInstance() {
		if (oggettoSingleton == null) {
			oggettoSingleton = new DBConnectionSingleton();
		}
		return oggettoSingleton;
	}

	private static Connection connessione;

	public Connection recuperaConnessione() throws ClassNotFoundException, SQLException {

		if (connessione == null || connessione.isClosed()) {
			Class.forName(DB_DRIVER_CLASS);
			connessione = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connessione effettuata con successo");
		}
		return connessione;
	}
			
// ---> DBConnectionSingleton.getInstance().recuperaConnection(); 




}
