package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author pedroRhamon
 */
public class DAO {

	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?userTimezone=true&serverTimezone=UTC";
	private final String user = "root";
	private final String password = "root";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
//	public void testeConexao() {
//		try {
//			Connection connection = conectar();
//			System.out.println(connection);
//			connection.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

}
