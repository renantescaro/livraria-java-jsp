package controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Inicio extends HttpServlet{
	//utilizo as request gets para redirecionamento de paginas
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		RequestDispatcher rq = request.getRequestDispatcher("inicio.jsp");
		rq.forward(request, response);
	}
}