package agenda.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import agenda.model.DAO;
import agenda.model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "update", "delete", "/report" })
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
		} else if (p_action.equals("/select")) {
			this.selecionarContato(request, response);
		}else if (p_action.equals("/update")) {
				this.atualizarContato(request, response);
		} else if (p_action.equals("/delete")) {
			this.excluir(request, response);
		} else if (p_action.equals("/report")) {
			this.gerarRelatorio(request, response);}
		else {
			response.sendRedirect("index.html");
		}
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline, filename=" + "contatos.pdf");
			
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.add(new Paragraph("Lista de contatos:"));
			document.add(new Paragraph(" "));
			
			PdfPTable table = new PdfPTable(3);
			
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome:"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone:"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email:"));
			
			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);
			
			List<JavaBeans> lista = dao.buscarContato();
			for(int i =0; i< lista.size(); i++) {
				table.addCell(lista.get(i).getNome());
				table.addCell(lista.get(i).getFone());
				table.addCell(lista.get(i).getEmail());
			}
			
			document.add(table);
			document.close();
		} catch (Exception e) {
			log("Gerando Arquivo em PDF");
			document.close();
		}
	}

	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.atualizarContato(contato);
		
		response.sendRedirect("main");
	}

	protected void contatosLista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JavaBeans> lista = this.dao.buscarContato();
		
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		
		response.sendRedirect("main");
	}
	
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		this.dao.selecionarContato(contato);
		
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		contato.setIdcon(idcon);
		this.dao.deletar(contato);
		response.sendRedirect("main");
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}

}
