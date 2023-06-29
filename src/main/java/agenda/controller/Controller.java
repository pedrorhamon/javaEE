package agenda.controller;

import java.io.IOException;
import java.util.List;

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
	}
	
	protected void contatosLista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JavaBeans> buscarContato = this.dao.buscarContato();
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		
		response.sendRedirect("main");
	}
	
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(contato.getIdcon() != null) {
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
		}
		throw new RuntimeException("Usuário já cadastrado!");
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}

}
