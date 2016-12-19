package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Json.JsonOperation;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		JsonOperation op = new JsonOperation();
		response.setContentType("text/javascript");
		String id = request.getParameter("id");
		if(id!=null&&id!=""){
			response.getWriter().print(op.tojson("all",null,id,null));
		}else
			response.getWriter().print(op.tojson("destination_number",null,null,null));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		JsonOperation op = new JsonOperation();
		response.setContentType("text/javascript");
		String id = request.getParameter("id");
		if(id!=null&&id!=""){
			response.getWriter().print(op.tojson("all",null,id,null));
		}else
			response.getWriter().print(op.tojson("destination_number",null,null,null));
	}

}
