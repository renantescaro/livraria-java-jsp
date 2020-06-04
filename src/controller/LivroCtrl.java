package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LivroDao;
import entity.Livro;

@WebServlet("/livro")
public class LivroCtrl extends HttpServlet{
	//utilizo as request gets para redirecionamento de paginas
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{

		String acao = "";
		if(request.getParameterMap().containsKey("acao")==true) {
			acao = request.getParameter("acao").toString();
		}
		
		//Listagem
		if(acao == null || acao==""){			
			LivroDao livroDao  = new LivroDao();
			List<Livro> livros = new ArrayList<Livro>();
			
			try{
				livros = livroDao.selecionarTudo();
			}catch(SQLException e){
				livros = null;
			}

			request.setAttribute("livros", livros);
			RequestDispatcher rq = request.getRequestDispatcher("/livro/listagem.jsp");
			rq.forward(request, response);
		}

		//Atualizar, Inserir e excluir
		else{
			Livro livro = null;

			//INSERIR
			if(acao.equals("inserir")){
				RequestDispatcher rq = request.getRequestDispatcher("/livro/cadastro.jsp");
				rq.forward(request, response);
			}else
			
			//ATUALIZAR
			if(acao.equals("atualizar")){
				try{
					LivroDao livroDao = new LivroDao();
					int idAtualizar = Integer.parseInt(request.getParameter("id"));
					livro = livroDao.selecionarPorId(idAtualizar);
				}catch (SQLException e){
					e.printStackTrace();
				}
				
				request.setAttribute("livro", livro);
				RequestDispatcher rq = request.getRequestDispatcher("/livro/cadastro.jsp");
				rq.forward(request, response);
			}else 
			
			//EXCLUIR
			if(acao.equals("excluir")){
				try{
					LivroDao livroDao = new LivroDao();
					int idExcluir = Integer.parseInt(request.getParameter("id"));
					livroDao.deletarPorId(idExcluir);
				}catch (SQLException e){
					e.printStackTrace();
				}
				response.sendRedirect("livro");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{

		Livro 	 livro    = new Livro();
		LivroDao livroDao = new LivroDao();
		
		//atualizar
		int id = 0;
		//verifica se � insert/update
		if(request.getParameterMap().containsKey("id")==true){
			try{
				id = (request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0);
			}catch(Exception e){
				id = 0;
				System.out.println("Erro converter id para inteiro");
			}			
		}
		
		String nome 		 = request.getParameter("nome");
		String numeroPaginas = request.getParameter("numeroPaginas");
		String idioma 		 = request.getParameter("idioma");
		String descricao 	 = request.getParameter("descricao");
		String isbn 	     = request.getParameter("isbn");
		String escritor      = request.getParameter("escritor");
		String editora 	     = request.getParameter("editora");

		//inserir ou atualiza
		try{
			livro.setNome(nome);
			livro.setNumeropaginas(data);

			//insere nova livro
			if(id==0){
				livro = livroDao.inserir(livro);

				if(livro != null){
					livroDao.inserir(livro);
				}else{
					System.out.println("livro nula");
				}
			}
			//atualiza pessoa existente
			else{
				livro.setId(id);
				livroDao.atualizar(livro);
			}
			//redireciona para listagem
			response.sendRedirect("livro");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
