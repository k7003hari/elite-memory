package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.log_model;
import controller.login_controller;
import java.io.IOException;

public class Log_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Log_serv() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log_model model=new log_model();
		model.setUname(request.getParameter("username"));
		model.setPass(request.getParameter("password"));
		 login_controller controller=new login_controller(model);
		 if(controller.check())
			 response.sendRedirect("Gp.html");
		 else {
			 System.out.println("INVALID DATA");
	            String alertScript = "<script>alert('Incorrect username or password'); window.history.back();</script>";
	            response.getWriter().print(alertScript);
		 }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
