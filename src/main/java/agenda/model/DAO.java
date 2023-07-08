package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	// Crud Update
	public void selecionarContato(JavaBeans contato) {
		String query = "select * from contatos where idcon = ?";
		try {
			Connection connection = conectar();

			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, contato.getIdcon());
			ResultSet executeUpdate = pst.executeQuery();

			while (executeUpdate.next()) {
				contato.setIdcon(executeUpdate.getString(1));
				contato.setNome(executeUpdate.getString(2));
				contato.setFone(executeUpdate.getString(3));
				contato.setEmail(executeUpdate.getString(4));
			}
			connection.close();
		} catch (Exception e) {}
	}
	
	public void atualizarContato(JavaBeans contato) {
		String create = "update contatos set nome=?,fone=?, email=?, where idcon=? ";
		try {
			Connection connection = conectar();
			PreparedStatement pst = connection.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {}
	}

	// Crud Read//
	public List<JavaBeans> buscarContato() {

		List<JavaBeans> contatos = new ArrayList<>();

		String lista = "select * from contatos order by nome";

		try {
			Connection connection = conectar();

			PreparedStatement pst = connection.prepareStatement(lista);

			ResultSet executeUpdate = pst.executeQuery();
			while (executeUpdate.next()) {
				String idcon = executeUpdate.getString(1);
				String nome = executeUpdate.getString(2);
				String fone = executeUpdate.getString(3);
				String email = executeUpdate.getString(4);

				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			pst.close();
			return contatos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contatos;
	}

	public void deletar(JavaBeans contato) {
		String create = "delele from contatos (nome,fone,emal) where values (?,?,?)";
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
