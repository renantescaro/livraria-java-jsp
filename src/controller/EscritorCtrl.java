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

import dao.EscritorDao;
import dao.PessoaDao;
import entity.Escritor;
import entity.Pessoa;

@WebServlet("/escritor")
public class EscritorCtrl extends HttpServlet{
	//utilizo as request gets para redirecionamento de paginas
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{

		String acao = "";
		if(request.getParameterMap().containsKey("acao")==true) {
			acao = request.getParameter("acao").toString();
		}
		
		//Listagem
		if(acao == null || acao==""){
			RequestDispatcher rq = request.getRequestDispatcher("/escritor/listagem.jsp");
			
			EscritorDao escritorDao = new EscritorDao();
			List<Escritor> escritores = new ArrayList<Escritor>();
			
			try{
				escritores = escritorDao.selecionarTudo();
			}catch(SQLException e){
				escritores = null;
			}
			
			request.setAttribute("escritores", escritores);
			rq.forward(request, response);
		}
		
		//Atualizar, Inserir e excluir
		else{
			Escritor escritor = null;
			
			//INSERIR
			if(acao.equals("inserir")){
				RequestDispatcher rq = request.getRequestDispatcher("/escritor/cadastro.jsp");
				rq.forward(request, response);
				
			}else
			
			//ATUALIZAR
			if(acao.equals("atualizar")){
				try{
					EscritorDao escritorDao = new EscritorDao();
					int idAtualizar = Integer.parseInt(request.getParameter("id"));
					escritor = escritorDao.selecionarPorId(idAtualizar);
				}catch (SQLException e){
					e.printStackTrace();
				}
				
				request.setAttribute("escritor", escritor);
				RequestDispatcher rq = request.getRequestDispatcher("/escritor/cadastro.jsp");
				rq.forward(request, response);
			}else 
			
			//ECLUIR
			if(acao.equals("excluir")){
				try{
					EscritorDao escritorDao = new EscritorDao();
					int idAtualizar = Integer.parseInt(request.getParameter("id"));
					escritorDao.deletarPorId(idAtualizar);
				}catch (SQLException e){
					e.printStackTrace();
				}
				response.sendRedirect("escritor");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{

		Pessoa 		pessoa 		= new Pessoa();
		PessoaDao 	pessoaDao 	= new PessoaDao();
		Escritor 	escritor 	= new Escritor();
		EscritorDao escritorDao = new EscritorDao();
		
		//atualizar
		int id = 0;
		//verifica se é insert/update
		if(request.getParameterMap().containsKey("id")==true){
			try{
				id = (request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0);
			}catch(Exception e){
				id = 0;
				System.out.println("Erro converter data");
			}			
		}
		
		String nome    	  = request.getParameter("nome");
		String dataStr    = request.getParameter("dataNascimento");
		String observacao = request.getParameter("observacao");
		
		//converte data
		java.util.Date dataTemporartia;
		java.sql.Date data = null;
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			dataTemporartia = (java.util.Date) sd.parse(dataStr);
			data = new java.sql.Date (dataTemporartia.getTime());
		}catch
			(ParseException e){
			System.out.println("Erro converter data");
		}

		//insere ou atualiza
		try{
			pessoa.setNome(nome);
			pessoa.setDataNascimento(data);
			pessoa.setObservacao(observacao);

			//insere nova pessoa
			if(id==0){
				pessoa = pessoaDao.inserir(pessoa);

				if(pessoa != null){
					escritor.setPessoa(pessoa);
					escritorDao.inserir(escritor);
				}else{
					System.out.println("pessoa nula");
				}
			}
			//atualiza pessoa existente
			else{
				pessoa.setId(id);
				pessoaDao.atualizar(pessoa);
			}
			//redireciona para listagem
			response.sendRedirect("escritor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
