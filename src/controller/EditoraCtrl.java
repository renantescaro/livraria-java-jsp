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

import dao.EditoraDao;
import dao.EmpresaDao;
import entity.Editora;
import entity.Empresa;

@WebServlet("/editora")
public class EditoraCtrl extends HttpServlet{
	//utilizo as request gets para redirecionamento de paginas
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{

		String acao = "";
		if(request.getParameterMap().containsKey("acao")==true) {
			acao = request.getParameter("acao").toString();
		}
		
		//Listagem
		if(acao == null || acao==""){			
			EditoraDao editoraDao  = new EditoraDao();
			List<Editora> editoras = new ArrayList<Editora>();
			
			try{
				editoras = editoraDao.selecionarTudo();
			}catch(SQLException e){
				editoras = null;
			}

			request.setAttribute("editoras", editoras);
			RequestDispatcher rq = request.getRequestDispatcher("/editora/listagem.jsp");
			rq.forward(request, response);
		}

		//Atualizar, Inserir e excluir
		else{
			Editora editora = null;

			//INSERIR
			if(acao.equals("inserir")){
				RequestDispatcher rq = request.getRequestDispatcher("/editora/cadastro.jsp");
				rq.forward(request, response);
			}else
			
			//ATUALIZAR
			if(acao.equals("atualizar")){
				try{
					EditoraDao editoraDao = new EditoraDao();
					int idAtualizar = Integer.parseInt(request.getParameter("id"));
					editora = editoraDao.selecionarPorId(idAtualizar);
				}catch (SQLException e){
					e.printStackTrace();
				}
				
				request.setAttribute("editora", editora);
				RequestDispatcher rq = request.getRequestDispatcher("/editora/cadastro.jsp");
				rq.forward(request, response);
			}else 
			
			//EXCLUIR
			if(acao.equals("excluir")){
				try{
					EditoraDao editoraDao = new EditoraDao();
					int idExcluir = Integer.parseInt(request.getParameter("id"));
					editoraDao.deletarPorId(idExcluir);
				}catch (SQLException e){
					e.printStackTrace();
				}
				response.sendRedirect("editora");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{

		Empresa 	empresa    = new Empresa();
		EmpresaDao 	empresaDao = new EmpresaDao();
		Editora 	editora    = new Editora();
		EditoraDao  editoraDao = new EditoraDao();
		
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
		String dataStr    = request.getParameter("dataAbertura");
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

		//inserir ou atualiza
		try{
			empresa.setNome(nome);
			empresa.setDataAbertura(data);

			//insere nova empresa
			if(id==0){
				empresa = empresaDao.inserir(empresa);

				if(empresa != null){
					editora.setEmpresa(empresa);
					editoraDao.inserir(editora);
				}else{
					System.out.println("empresa nula");
				}
			}
			//atualiza pessoa existente
			else{
				empresa.setId(id);
				empresaDao.atualizar(empresa);
			}
			//redireciona para listagem
			response.sendRedirect("editora");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
