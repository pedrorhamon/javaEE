package agenda.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.model.DAO;
import agenda.model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_action = request.getServletPath();
		if (p_action.equals("/main")) {
			this.contatos(request, response);
		} else if (p_action.equals("/insert")) {
			this.novoContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		// dao.testeConexao();
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}

}
