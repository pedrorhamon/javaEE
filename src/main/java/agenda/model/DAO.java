package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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

	// Crud Created//
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,emal) values (?,?,?)";

		try {
			Connection connection = conectar();

			PreparedStatement pst = connection.prepareStatement(create);

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Crud Read//
	public List<JavaBeans> buscarContato() {
		String lista = "select * from contatos order by nome";
		
		try {
			Connection connection = conectar();

			PreparedStatement pst = connection.prepareStatement(lista);
			
			ResultSet executeUpdate = pst.executeQuery();
			while(executeUpdate.next()) {
				String idcon = executeUpdate.getString(1);
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
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
